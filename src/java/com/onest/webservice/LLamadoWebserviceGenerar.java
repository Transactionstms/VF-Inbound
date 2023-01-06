/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onest.webservice;

import com.bean.DestinoHiltiBean;
import com.mx.hilti.service.DocumentacionSOAP;
import com.paquetexpress.www.webbooking.Documentacion.ClienteDestino;
import com.paquetexpress.www.webbooking.Documentacion.DatoAdicional;
import com.paquetexpress.www.webbooking.Documentacion.DatosAdicionales;
import com.paquetexpress.www.webbooking.Documentacion.Embarque;
import com.paquetexpress.www.webbooking.Documentacion.Header;
import com.paquetexpress.www.webbooking.Documentacion.OtroServicio;
import com.paquetexpress.www.webbooking.Documentacion.Servicios;
import com.paquetexpress.www.webbooking.Documentacion.SolicitudEnvio;
import com.paquetexpress.www.webbooking.Documentacion.TypeEvent;
import com.paquetexpress.www.webbooking.Documentacion.retorno.DataResponse;

/**
 *
 * @author RicardoMartinez
 */
public class LLamadoWebserviceGenerar {
        
  public DataResponse getDocumentoGuia( DestinoHiltiBean destinoHiltiBean, String piezas){
      DocumentacionSOAP soap = new DocumentacionSOAP();
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
			parameters.getHeader().setTypeEvent( TypeEvent.Generar  ); //GENERARR - GENERAR REGISTROS
			
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
			//parameters.getSolicitudEnvio().getClienteDestino().setDomicilioDestino(new DomicilioDestino("LIBRAMIENTO A SALTILLO", "KM 27.5", "CENTRO", "66050", "ABCD@DOMINIO.COM", "222222222")  );
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
			
			
			parameters.getSolicitudEnvio().setDetalleEmbarque(new com.paquetexpress.www.webbooking.Documentacion.DetalleEmbarque());

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
			 DatoAdicional datoAdicional = new DatoAdicional();
        datoAdicional.setClaveDataAd("rfcDest");
        datoAdicional.setValorDataAd(destinoHiltiBean.getRfc());
        datoAdicional.setDataAditional1("");
        datoAdicional.setDataAditional2("");
        datoAdicional.setDataAditional3("");
        datoAdicional.setDataAditional4("");
        
        com.paquetexpress.www.webbooking.Documentacion.DatoAdicional[] datosAdicionales = {datoAdicional};
        
        parameters.getSolicitudEnvio().setDatosAdicionales(new DatosAdicionales());
        parameters.getSolicitudEnvio().getDatosAdicionales().setDatoAdicional(datosAdicionales);
        
			parameters.getSolicitudEnvio().setCustAgnt(" ");
			parameters.getSolicitudEnvio().setGhPediNumb(" ");
                        
                        
      DataResponse  dataResponse = soap.documentacionGuia(parameters);
      
      return dataResponse;
  }
}
