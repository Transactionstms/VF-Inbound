/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tacts.rest;

import com.tacts.dao.TmsCustom;
import com.tacts.model.CustomModel;
import com.tacts.model.MensajeModel;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo; 
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author grece
 */
@Path("customs")
public class UbicacionResource {

    @Context
    private UriInfo context;

    public UbicacionResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void putHtml1(String content) {
    }

    @POST
    @Path("consultas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON) 
    public MensajeModel guardar(
            CustomModel objetosBody,
            @HeaderParam("Token") String token, 
            @HeaderParam("UsuarioManager") String usr) {
        
        MensajeModel mensaje =new MensajeModel(); 
        TmsCustom consultar = new TmsCustom();

        String mensajeConsulta=""; 
        String cifradoToken = "";
        String usuario = "";
        String agenteId = "";
        
        if(usr.equals("ALogIxIDh96j#")){          //LOGIX         
            cifradoToken = "TaCtsCusToM@nag3r#";
            usuario = "ALogIxIDh96j#"; 
            agenteId = "4001";
        }else if(usr.equals("ACusaIDm75h#")){     //CUSA
            cifradoToken = "TaCtsCusToM@nag3r#";
            usuario = "ACusaIDm75h#";  
            agenteId = "4002";
        }else if(usr.equals("ARadarIDg95j#")){    //RADAR
            cifradoToken = "T@CtsCusTom#Manag3r";
            usuario = "ARadarIDg95j#";     
            agenteId = "4003";
        }else if(usr.equals("ASesmaIDL94c#")){    //SESMA
            cifradoToken = "TaCtsCusToM@nag3r#";
            usuario = "ASesmaIDL94c#";    
            agenteId = "4004";
        }else if(usr.equals("AR3chyIDy56w#")){    //RECHY
            cifradoToken = "T@CtsCusTom#Manag3r";
            usuario = "AR3chyIDy56w#";  
            agenteId = "4005";
        }else if(usr.equals("AvFIDq34v#")){       //VF
            cifradoToken = "T@CtsCusTom#Manag3r";
            usuario = "AvFIDq34v#";    
            agenteId = "4006";
        }
        
          try{
                if (usr.equals(usuario) && token.equals(cifradoToken)) {
                    
                    List<CustomModel> lista = new ArrayList<>();
                    lista.add(objetosBody); 
                    
                        mensajeConsulta=   consultar.registrosCustoms(objetosBody,agenteId); 
                        mensaje.setData    (mensajeConsulta);
                }else{
                        mensajeConsulta=   "Incorrect Authentication";
                        mensaje.setMensaje (mensajeConsulta);
                }
        
          }catch( Exception e){
                    return null;
          }
          
        return mensaje;
    }

    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml2(String content) {
    }
}
