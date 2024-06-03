/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.dao;

import com.tacts.sql.Conexion;
import java.sql.CallableStatement;

/**
 *
 * @author Desarrollo Tacts
 */
public class CustomsSql {
    
    public static CallableStatement consultarCustomLOGIX(Conexion conexion,String agenteId)throws Exception{
        try {
            CallableStatement sp = conexion.getConexion().prepareCall("{ call SP_INB_CUSTOMS_WSCONSUM_LOGIX(?,?) }");
            sp.setString("pAgenteAduanal", agenteId);
            sp.registerOutParameter("resultado", -10);
            return sp; 
        } catch (Exception exception) {
            System.out.println("--"+exception);
            return null; 
        }
    } 
    
    public static CallableStatement actualizarCustomsLogix(Conexion conexion, 
            String evento_traficotms,
            String shipmentId_traficotms,
            String countryOrigin_traficotms,
            String sizeContainer_traficotms,
            String valueDlls_traficotms,
            String etaPortDischargeTwo_traficotms,
            String AA_traficotms,
            String yearPed_traficotms,
            String aaPat_traficotms,
            String customHouse_traficotms,
            String noPed_traficotms,
            String noPedRect1_traficotms,
            String noPedComment1_traficotms,
            String noPedRect2_traficotms,
            String noPedComment2_traficotms,
            String DocumentRecepcionDate_traficotms,
            String enclosure_traficotms,
            String shippingCompany_traficotms,
            String vessel_traficotms,
            String revalidationDate_traficotms,
            String d_previousOrigin_traficotms,
            String d_previousDestiny_traficotms,
            String d_previousResult_traficotms,
            String finalProforma_traficotms,
            String permissionRequired_traficotms,
            String d_sendTokens_traficotms,
            String d_receiptPermitsProcessed_traficotms,
            String d_PermitActivation_traficotms,
            String d_AuthorizedPermits_traficotms,
            String AccountwithCO_traficotms,
            String TariffPreferenceCO_traficotms,
            String requiresUVA_traficotms,
            String RequiresCA_traficotms,
            String d_receiptCA_traficotms,
            String certificateNumberCA_traficotms,
            String amountCA_traficotms,
            String d_completeDocuments_traficotms,
            String d_paidPed_traficotms,
            String d_transportRequest_traficotms,
            String d_modulation_traficotms,
            String modality_traficotms,
            String modulationResult_traficotms,
            String d_recognition_traficotms,
            String d_release_traficotms,
            String originSeal_traficotms,
            String endStamp_traficotms,
            String d_retentionAuthority_traficotms,
            String d_withHoldingAuthorityRelease_traficotms,
            String OperationStatus_traficotms,
            String reasonDelay_traficotms,
            String comments_traficotms,
            String arrivalNOVA_traficotms,
            String arrivalGlobalTradeSD_traficotms,
            String archiveM_traficotms,
            String d_archiveM_traficotms,
            String d_requestHandling_traficotms,
            String d_handlingExpiration_traficotms,
            String d_keyConfirmationPed_traficotms,
            String d_incrementalReception_traficotms,
            String tande_traficotms,
            String d_expirationInbound_traficotms,
            String d_register_traficotms,
            String d_updated_traficotms)
    {
        try {
           
            CallableStatement sp = conexion.getConexion().prepareCall("{call SP_INB_WS_CONSUMO_UPLOAD_LOGIX_CUSTOMS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            
             sp.setString("evento_traficotms", evento_traficotms);
             sp.setString("shipmentId_traficotms", shipmentId_traficotms);
             sp.setString("countryOrigin_traficotms", countryOrigin_traficotms);
             sp.setString("sizeContainer_traficotms", sizeContainer_traficotms);
             sp.setString("valueDlls_traficotms", valueDlls_traficotms);
             sp.setString("etaPortDischargeTwo_traficotms", etaPortDischargeTwo_traficotms);
             sp.setString("AA_traficotms", AA_traficotms);
             sp.setString("yearPed_traficotms", AA_traficotms);
             sp.setString("aaPat_traficotms", aaPat_traficotms);
             sp.setString("customHouse_traficotms", customHouse_traficotms);
             sp.setString("noPed_traficotms", noPed_traficotms);
             sp.setString("noPedRect1_traficotms", noPedRect1_traficotms);
             sp.setString("noPedComment1_traficotms", noPedComment1_traficotms);
             sp.setString("noPedRect2_traficotms", noPedRect2_traficotms);
             sp.setString("noPedComment2_traficotms", noPedComment2_traficotms);
             sp.setString("DocumentRecepcionDate_traficotms", DocumentRecepcionDate_traficotms);
             sp.setString("enclosure_traficotms", enclosure_traficotms);
             sp.setString("shippingCompany_traficotms", shippingCompany_traficotms);
             sp.setString("vessel_traficotms", vessel_traficotms);
             sp.setString("revalidationDate_traficotms", revalidationDate_traficotms);
             sp.setString("d_previousOrigin_traficotms", d_previousOrigin_traficotms);
             sp.setString("d_previousDestiny_traficotms", d_previousDestiny_traficotms);
             sp.setString("d_previousResult_traficotms", d_previousResult_traficotms);
             sp.setString("finalProforma_traficotms", finalProforma_traficotms);
             sp.setString("permissionRequired_traficotms", permissionRequired_traficotms);
             sp.setString("d_sendTokens_traficotms", d_sendTokens_traficotms);
             sp.setString("d_receiptPermitsProcessed_traficotms", d_receiptPermitsProcessed_traficotms);
             sp.setString("d_PermitActivation_traficotms", d_PermitActivation_traficotms);
             sp.setString("d_AuthorizedPermits_traficotms", d_AuthorizedPermits_traficotms);
             sp.setString("AccountwithCO_traficotms", AccountwithCO_traficotms);
             sp.setString("TariffPreferenceCO_traficotms", TariffPreferenceCO_traficotms);
             sp.setString("requiresUVA_traficotms", requiresUVA_traficotms);
             sp.setString("RequiresCA_traficotms", RequiresCA_traficotms);
             sp.setString("d_receiptCA_traficotms", d_receiptCA_traficotms);
             sp.setString("certificateNumberCA_traficotms", certificateNumberCA_traficotms);
             sp.setString("amountCA_traficotms", amountCA_traficotms);
             sp.setString("d_completeDocuments_traficotms", d_completeDocuments_traficotms);
             sp.setString("d_paidPed_traficotms", d_paidPed_traficotms);
             sp.setString("d_transportRequest_traficotms", d_transportRequest_traficotms);
             sp.setString("d_modulation_traficotms", d_modulation_traficotms);
             sp.setString("modality_traficotms", modality_traficotms);
             sp.setString("modulationResult_traficotms", modulationResult_traficotms);
             sp.setString("d_recognition_traficotms", d_recognition_traficotms);
             sp.setString("d_release_traficotms", d_release_traficotms);
             sp.setString("originSeal_traficotms", originSeal_traficotms);
             sp.setString("endStamp_traficotms", endStamp_traficotms);
             sp.setString("d_retentionAuthority_traficotms", d_retentionAuthority_traficotms);
             sp.setString("d_withHoldingAuthorityRelease_traficotms", d_withHoldingAuthorityRelease_traficotms);
             sp.setString("OperationStatus_traficotms", OperationStatus_traficotms);
             sp.setString("reasonDelay_traficotms", reasonDelay_traficotms);
             sp.setString("comments_traficotms", comments_traficotms);
             sp.setString("arrivalNOVA_traficotms", arrivalNOVA_traficotms);
             sp.setString("arrivalGlobalTradeSD_traficotms", arrivalGlobalTradeSD_traficotms);
             sp.setString("archiveM_traficotms", archiveM_traficotms);
             sp.setString("d_archiveM_traficotms", d_archiveM_traficotms);
             sp.setString("d_requestHandling_traficotms", d_requestHandling_traficotms);
             sp.setString("d_handlingExpiration_traficotms", d_handlingExpiration_traficotms);
             sp.setString("d_keyConfirmationPed_traficotms", d_keyConfirmationPed_traficotms);
             sp.setString("d_incrementalReception_traficotms", d_incrementalReception_traficotms);
             sp.setString("tande_traficotms", tande_traficotms);
             sp.setString("d_expirationInbound_traficotms", d_expirationInbound_traficotms);
             sp.setString("d_register_traficotms", d_register_traficotms);
             sp.setString("d_updated_traficotms", d_updated_traficotms);
             sp.registerOutParameter("resultado", -10);
            return sp;
            
        } catch (Exception ex) {
            System.out.println("Error al reproducir store procedure (SP_INB_WS_CONSUMO_UPLOAD_LOGIX_CUSTOMS): " + ex.toString());
        }
        return null;
    } 
    
}
