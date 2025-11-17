package com.bodegapp.personal.model;

public class PersonalModel {
    
    private int ID_USER;
    private String NOMBRE_COMPLETO;
    private String CARGO;
    private double SALARIO;
    private String TIPO_CONTRATO;
    private boolean ESTADO;
    private String CORREO;
    private String CEDULA;
    private boolean ES_VENDEDOR;
    private String CODIGO_VENDEDOR;

    public PersonalModel() {
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    public String getNOMBRE_COMPLETO() {
        return NOMBRE_COMPLETO;
    }

    public void setNOMBRE_COMPLETO(String NOMBRE_COMPLETO) {
        this.NOMBRE_COMPLETO = NOMBRE_COMPLETO;
    }

    public String getCARGO() {
        return CARGO;
    }

    public void setCARGO(String CARGO) {
        this.CARGO = CARGO;
    }

    public double getSALARIO() {
        return SALARIO;
    }

    public void setSALARIO(double SALARIO) {
        this.SALARIO = SALARIO;
    }

    public String getTIPO_CONTRATO() {
        return TIPO_CONTRATO;
    }

    public void setTIPO_CONTRATO(String TIPO_CONTRATO) {
        this.TIPO_CONTRATO = TIPO_CONTRATO;
    }

    public boolean isESTADO() {
        return ESTADO;
    }

    public void setESTADO(boolean ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public String getCEDULA() {
        return CEDULA;
    }

    public void setCEDULA(String CEDULA) {
        this.CEDULA = CEDULA;
    }

    public boolean isES_VENDEDOR() {
        return ES_VENDEDOR;
    }

    public void setES_VENDEDOR(boolean ES_VENDEDOR) {
        this.ES_VENDEDOR = ES_VENDEDOR;
    }

    public String getCODIGO_VENDEDOR() {
        return CODIGO_VENDEDOR;
    }

    public void setCODIGO_VENDEDOR(String CODIGO_VENDEDOR) {
        this.CODIGO_VENDEDOR = CODIGO_VENDEDOR;
    }
}

