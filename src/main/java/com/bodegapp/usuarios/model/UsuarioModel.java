package com.bodegapp.usuarios.model;

import java.util.Date;

public class UsuarioModel {
    
    private int ID_USER;
    private int ID_EMPRESA;
    private String ID_ROL;
    private  String NOMBRE1;
    private  String NOMBRE2;
    private  String APELLIDO1;
    private  String APELLIDO2;
    private  String CEDULA;
    private  String CORREO;
    private  String CONTRASENA_HASH;
    private Date FECHA_INGRESO;
    private double SALARIO;
    private String TIPO_CONTRATO;
    private boolean ESTADO;

    public UsuarioModel(int ID_EMPRESA, String ID_ROL, String NOMBRE1, String NOMBRE2, String APELLIDO1, String APELLIDO2, String CEDULA, String CORREO, String CONTRASENA_HASH, Date FECHA_INGRESO, double SALARIO, String TIPO_CONTRATO, boolean ESTADO) {
        this.ID_EMPRESA = ID_EMPRESA;
        this.ID_ROL = ID_ROL;
        this.NOMBRE1 = NOMBRE1;
        this.NOMBRE2 = NOMBRE2;
        this.APELLIDO1 = APELLIDO1;
        this.APELLIDO2 = APELLIDO2;
        this.CEDULA = CEDULA;
        this.CORREO = CORREO;
        this.CONTRASENA_HASH = CONTRASENA_HASH;
        this.FECHA_INGRESO = FECHA_INGRESO;
        this.SALARIO = SALARIO;
        this.TIPO_CONTRATO = TIPO_CONTRATO;
        this.ESTADO = ESTADO;
    }

    public UsuarioModel() {
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }

    public int getID_EMPRESA() {
        return ID_EMPRESA;
    }

    public void setID_EMPRESA(int ID_EMPRESA) {
        this.ID_EMPRESA = ID_EMPRESA;
    }

    public String getID_ROL() {
        return ID_ROL;
    }

    public void setID_ROL(String ID_ROL) {
        this.ID_ROL = ID_ROL;
    }

    public String getNOMBRE1() {
        return NOMBRE1;
    }

    public void setNOMBRE1(String NOMBRE1) {
        this.NOMBRE1 = NOMBRE1;
    }

    public String getNOMBRE2() {
        return NOMBRE2;
    }

    public void setNOMBRE2(String NOMBRE2) {
        this.NOMBRE2 = NOMBRE2;
    }

    public String getAPELLIDO1() {
        return APELLIDO1;
    }

    public void setAPELLIDO1(String APELLIDO1) {
        this.APELLIDO1 = APELLIDO1;
    }

    public String getAPELLIDO2() {
        return APELLIDO2;
    }

    public void setAPELLIDO2(String APELLIDO2) {
        this.APELLIDO2 = APELLIDO2;
    }

    public String getCEDULA() {
        return CEDULA;
    }

    public void setCEDULA(String CEDULA) {
        this.CEDULA = CEDULA;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public String getCONTRASENA_HASH() {
        return CONTRASENA_HASH;
    }

    public void setCONTRASENA_HASH(String CONTRASENA_HASH) {
        this.CONTRASENA_HASH = CONTRASENA_HASH;
    }

    public Date getFECHA_INGRESO() {
        return FECHA_INGRESO;
    }

    public void setFECHA_INGRESO(Date FECHA_INGRESO) {
        this.FECHA_INGRESO = FECHA_INGRESO;
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
    
    
    
}
