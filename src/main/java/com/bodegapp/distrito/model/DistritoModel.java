package com.bodegapp.distrito.model;

public class DistritoModel {
    private String codigoDistrito;
    private String nombreDistrito;

    public DistritoModel() {}

    public DistritoModel(String codigoDistrito, String nombreDistrito) {
        this.codigoDistrito = codigoDistrito;
        this.nombreDistrito = nombreDistrito;
    }

    public String getCodigoDistrito() {
        return codigoDistrito;
    }

    public void setCodigoDistrito(String codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }
}
