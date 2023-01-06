/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.documento;

import com.documento.pojos.DocumentoEmbarque;
import com.usuario.Usuario;

/**
 *
 * @author Omar
 */
public class BLDocumento {
    public static DocumentoEmbarque getDocumento(String embarque, Usuario usuario){    
        DaoDocumento daoDocumento =  new DaoDocumento();
        DocumentoEmbarque documentoEmbarque= null;
        try{
        documentoEmbarque= daoDocumento.getDocumento(embarque, usuario);
        }catch(Exception exc){
            exc.printStackTrace();
            documentoEmbarque=null;
        } 
        return documentoEmbarque;
    }
}
