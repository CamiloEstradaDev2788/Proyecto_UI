package com.bodegapp.clientes.model;

import java.sql.Date;

public class ClienteModel {
    
    private String CODIGO_CLIENTE;
    private String NOMBRE_CLIENTE;
    private String DIRECCION_CLIENTE;
    private String TELEFONO_CLIENTE;
    private String RUC_CLIENTE;
    private Date FECHA_REGISTRO;
    private String TIPO_CLIENTE;
    private String CONDICION_CLIENTE;
    private String CODIGO_DISTRITO;

    public ClienteModel() {
    }

    public ClienteModel(String CODIGO_CLIENTE, String NOMBRE_CLIENTE, String DIRECCION_CLIENTE, String TELEFONO_CLIENTE, String RUC_CLIENTE, Date FECHA_REGISTRO, String TIPO_CLIENTE, String CONDICION_CLIENTE, String CODIGO_DISTRITO) {
        this.CODIGO_CLIENTE = CODIGO_CLIENTE;
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
        this.DIRECCION_CLIENTE = DIRECCION_CLIENTE;
        this.TELEFONO_CLIENTE = TELEFONO_CLIENTE;
        this.RUC_CLIENTE = RUC_CLIENTE;
        this.FECHA_REGISTRO = FECHA_REGISTRO;
        this.TIPO_CLIENTE = TIPO_CLIENTE;
        this.CONDICION_CLIENTE = CONDICION_CLIENTE;
        this.CODIGO_DISTRITO = CODIGO_DISTRITO;
    }

    public String getCODIGO_CLIENTE() {
        return CODIGO_CLIENTE;
    }

    public void setCODIGO_CLIENTE(String CODIGO_CLIENTE) {
        this.CODIGO_CLIENTE = CODIGO_CLIENTE;
    }

    public String getNOMBRE_CLIENTE() {
        return NOMBRE_CLIENTE;
    }

    public void setNOMBRE_CLIENTE(String NOMBRE_CLIENTE) {
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
    }

    public String getDIRECCION_CLIENTE() {
        return DIRECCION_CLIENTE;
    }

    public void setDIRECCION_CLIENTE(String DIRECCION_CLIENTE) {
        this.DIRECCION_CLIENTE = DIRECCION_CLIENTE;
    }

    public String getTELEFONO_CLIENTE() {
        return TELEFONO_CLIENTE;
    }

    public void setTELEFONO_CLIENTE(String TELEFONO_CLIENTE) {
        this.TELEFONO_CLIENTE = TELEFONO_CLIENTE;
    }

    public String getRUC_CLIENTE() {
        return RUC_CLIENTE;
    }

    public void setRUC_CLIENTE(String RUC_CLIENTE) {
        this.RUC_CLIENTE = RUC_CLIENTE;
    }

    public Date getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(Date FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

    public String getTIPO_CLIENTE() {
        return TIPO_CLIENTE;
    }

    public void setTIPO_CLIENTE(String TIPO_CLIENTE) {
        this.TIPO_CLIENTE = TIPO_CLIENTE;
    }

    public String getCONDICION_CLIENTE() {
        return CONDICION_CLIENTE;
    }

    public void setCONDICION_CLIENTE(String CONDICION_CLIENTE) {
        this.CONDICION_CLIENTE = CONDICION_CLIENTE;
    }

    public String getCODIGO_DISTRITO() {
        return CODIGO_DISTRITO;
    }

    public void setCODIGO_DISTRITO(String CODIGO_DISTRITO) {
        this.CODIGO_DISTRITO = CODIGO_DISTRITO;
    }
    
    
}
