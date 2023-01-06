/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.menu;

import java.util.List;
import com.usuario.Bodega;

/**
 *
 * @author Omar
 */
public class BodegaFactory {
    
    public static String setListado(List<Bodega> bodegas,int clave) {
        String listado="";
        try{
        if(bodegas.size()>1){
        listado="<select id=\"cambio\" style=\"width: 100%\">" 
                    +"<option value=\"0\" checked>Seleccione</option>";
        for(int i=0;i<bodegas.size();i++){
            if(bodegas.get(i).getCbdivID()==clave){
                listado+="<option value='" + bodegas.get(i).getCbdivID() + "' cuenta='" + bodegas.get(i).getCuentaId() + "' bodega='" + bodegas.get(i).getBodegaId() + "' bodegan='" + bodegas.get(i).getBodegaNombre() + "' selected >" + bodegas.get(i).getCuentaNombre() + "</option>";
            }else{
                listado+="<option value='" + bodegas.get(i).getCbdivID() + "' cuenta='" + bodegas.get(i).getCuentaId() + "' bodega='" + bodegas.get(i).getBodegaId() + "' bodegan='" + bodegas.get(i).getBodegaNombre() + "'>" + bodegas.get(i).getCuentaNombre() + "</option>";
            }                    
        }
        listado+="</select>";
        }else
            listado+="<label>"+bodegas.get(0).getCuentaNombre()+"</label>";
        }catch(Exception ex){
            listado="";
        }
        return listado;
    }
    
}
