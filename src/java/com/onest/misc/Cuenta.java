package com.onest.misc;

import java.io.Serializable;

public class Cuenta implements Serializable {

    private String idCuenta;
    private String idBodega;
    private String nombreCuenta;
    private String nombreBodega;
    private String validaLote;
    private String validaIdentificador;

    public Cuenta(String idCta, String idBod, String nombreCta, String nombreBod, String validaLote, String validaIdentificador) {
        this.idCuenta = idCta;
        this.idBodega = idBod;
        this.nombreCuenta = nombreCta;
        this.nombreBodega = nombreBod;
        this.validaLote = validaLote;
        this.validaIdentificador = validaIdentificador;
    }

    public String getIdCta() {
        return this.idCuenta;
    }

    public String getIdBod() {
        return this.idBodega;
    }

    public String getNombreCta() {
        return this.nombreCuenta;
    }

    public String getNombreBod() {
        return this.nombreBodega;
    }

    public String getValidaLote() {
        return this.validaLote;
    }

    public String getValidaId() {
        return this.validaIdentificador;
    }
}