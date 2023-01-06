package com.transactions.mailsender.Dao;

import java.util.Date;

public class ModelDataBase {
    private String DoctoEstadoId;
    private String ReferenceNumber;
    private String DoctoSalId;
    private String CbDivId;
    private String NombreCliente;
    private String Correo;
    private Date DoctoFecha;

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
    
    public void setDoctoFecha(Date value){
        this.DoctoFecha = value;
    }
    public Date getDoctoFecha(){
        return this.DoctoFecha;
    }
    
    public void setCbDivId(String value){
        this.CbDivId = value;
    }
    
    public String getCbDivId(){
        return this.CbDivId;
    }
    
    public void setDoctoSalId(String value){
        this.DoctoSalId = value;
    }
    
    public String getDoctoSalId(){
        return this.DoctoSalId;
    }
    
    public void setDoctoEstadoId(String value){
        this.DoctoEstadoId = value;
    }
    
    public String getDoctoEstadoId(){
        return this.DoctoEstadoId;
    }
    
    public void setReferenceNumber(String value){
        this.ReferenceNumber = value;
    }
    
    public String getReferenceNumber(){
        return this.ReferenceNumber;
    }
}
