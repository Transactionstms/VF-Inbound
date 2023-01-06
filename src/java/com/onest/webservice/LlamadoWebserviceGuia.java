/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.webservice;

import com.bean.DestinoHiltiBean;
import com.mx.hilti.service.DocumentacionSOAP;
import com.paquetexpress.www.webbooking.Documentacion.*;
import com.paquetexpress.www.webbooking.Documentacion.retorno.DataResponse;

/**
 *
 * @author SergioCastro
 */
public class LlamadoWebserviceGuia {
    
  public DataResponse getDocumentoGuia( DestinoHiltiBean destinoHiltiBean, String piezas){
      com.mx.hilti.service.DocumentacionSOAP soap = new com.mx.hilti.service.DocumentacionSOAP();
        Double  d = 0.0;
        Double.parseDouble(piezas);
        if(!piezas.equals("")){
            d = Double.parseDouble(piezas);
        }
       
        if(destinoHiltiBean==null){
            destinoHiltiBean = new DestinoHiltiBean();
        }
        
       
      int valor =  d.intValue(); ;
      com.paquetexpress.www.webbooking.Documentacion.Data parameters = new com.paquetexpress.www.webbooking.Documentacion.Data();
     
      destinoHiltiBean.setStrtName("130 CONSTRUCCIONES S.A DE C.V");
      destinoHiltiBean.setColonyName("REAL CENTENARIO"); 
      destinoHiltiBean.setZipCode("28984");
      destinoHiltiBean.setDestCustClntId("20260573");
      
      
      
      parameters.setHeader(new Header());
			parameters.getHeader().setOrgnClntId("30168");
			parameters.getHeader().setClntPswd("30168");
			parameters.getHeader().setAgreementKey("98AABC2D6DB4455AA6719739DDF870F1");
		

			
			parameters.getHeader().getTypeEvent();
			parameters.getHeader().setTypeEvent( TypeEvent.Calcular  ); //GENERARR - GENERAR REGISTROS
			
			parameters.setSolicitudEnvio(new SolicitudEnvio());
			parameters.getSolicitudEnvio().setClienteDestino(new ClienteDestino());
			parameters.getSolicitudEnvio().getClienteDestino().getDomicilioDestino().setStrtName(destinoHiltiBean.getStrtName());
        parameters.getSolicitudEnvio().getClienteDestino().getDomicilioDestino().setDrnr(" ");
        parameters.getSolicitudEnvio().getClienteDestino().getDomicilioDestino().setColonyName(destinoHiltiBean.getColonyName());
        parameters.getSolicitudEnvio().getClienteDestino().getDomicilioDestino().setZipCode(destinoHiltiBean.getZipCode());
        parameters.getSolicitudEnvio().getClienteDestino().getDomicilioDestino().setAmMailId(" ");
        parameters.getSolicitudEnvio().getClienteDestino().getDomicilioDestino().setPhoneNo(" ");
        parameters.getSolicitudEnvio().getClienteDestino().getDomicilioDestino().setDestinationRefDom(" ");
			parameters.getSolicitudEnvio().getClienteDestino().setDestCustClntId(destinoHiltiBean.getDestCustClntId()); //SEGUNDO EXCELQUE NOS ENVIAN
			parameters.getSolicitudEnvio().getClienteDestino().setDestClntName(" ");
			//parameters.getSolicitudEnvio().getClienteDestino().setDestCustClntId(destinoId); //SEGUNDO EXCELQUE NOS ENVIAN
			Embarque embarque1 = new Embarque();//new Embarque(1, "2", "refacciones", "", 12.0, .3, 50.0, 20.0, 10.0);
                        embarque1.setQuantity( valor );
                        embarque1.setShpCode(String.valueOf(valor));
                        embarque1.setContent("refacciones");
			Embarque embarque2 = new Embarque();//new Embarque(1, "3", "refacciones", "", 12.0, 3.0, 50.0, 20.0, 10.0)
			Embarque embarque3 = new Embarque();//new Embarque(1, "3", "refacciones", "", 12.0, 3.0, 50.0, 20.0, 10.0)
                        Embarque embarque4 = new Embarque();//new Embarque(1, "4", "refacciones", "", 12.0, 3.0, 50.0, 20.0, 10.0)
			Embarque embarque5 = new Embarque();//new Embarque(1, "5", "refacciones", "", 12.0, 3.0, 50.0, 20.0, 10.0)
                        Embarque embarque6 = new Embarque();//new Embarque(1, "5", "refacciones", "", 12.0, 3.0, 50.0, 20.0, 10.0)
			Embarque[] embarque = {embarque1};		
			
			
			parameters.getSolicitudEnvio().setDetalleEmbarque( new DetalleEmbarque() );
			parameters.getSolicitudEnvio().getDetalleEmbarque().setEmbarque(embarque);
                        
			
			OtroServicio otroServicio1 = new  OtroServicio("1", "prueba 1", "data 1", "dato 2");
			OtroServicio otroServicio2 = new  OtroServicio("2", "prueba 2", "data 2", "dato 2");
			OtroServicio [] otrosServicios = { otroServicio1,otroServicio2 };
			
			//parameters.getSolicitudEnvio().setServicios(new Servicios(otrosServicios , "2", "I", 0.0, 1000.0, " ", "SDFSDFDSF", "GFHGFHGFH"));
			parameters.getSolicitudEnvio().setServicios(new Servicios());
			//parameters.getSolicitudEnvio().getServicios().setOtrosServicios(otrosServicios);
			parameters.getSolicitudEnvio().getServicios().setDlvyType("2");
			parameters.getSolicitudEnvio().getServicios().setAckType("C");
			//parameters.getSolicitudEnvio().getServicios().setTotlDeclVlue(1000.0);
			parameters.getSolicitudEnvio().getServicios().setComments("SDFSDFDSF");
			parameters.getSolicitudEnvio().getServicios().setReference("GFHGFHGFH");
			parameters.getSolicitudEnvio().getServicios().setInvType("1");
			
			parameters.getSolicitudEnvio().setCustAgnt("6666");
			parameters.getSolicitudEnvio().setGhPediNumb("7777");
                        
                        
      DataResponse  dataResponse = soap.documentacionGuia(parameters);
      
      return dataResponse;
  }
    
}
