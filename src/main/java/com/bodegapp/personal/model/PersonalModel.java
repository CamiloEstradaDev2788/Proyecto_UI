package com.bodegapp.personal.model;

import java.util.Date;

public class PersonalModel {
    private int ID_USER;
    private int ID_EMPRESA;
    private int ID_ROL;

    private String NOMBRE1;
    private String NOMBRE2;
    private String APELLIDO1;
    private String APELLIDO2;

    private String CEDULA;
    private String CORREO;
    private String CONTRASENA_HASH; // en registrar puedes encriptar o guardar temporalmente
    private Date FECHA_INGRESO;

    private double SALARIO;
    private String TIPO_CONTRATO;
    private boolean ESTADO;

    // Vendedor
    private boolean ES_VENDEDOR;
    private String CODIGO_VENDEDOR;
    private String CODIGO_DISTRITO;
    private Date FECHA_INGRESO_VENDEDOR;
    private String TIPO_VENDEDOR;
    
    private double TOTAL_VENTAS;

    public double getTOTAL_VENTAS() { return TOTAL_VENTAS; }
    public void setTOTAL_VENTAS(double TOTAL_VENTAS) { this.TOTAL_VENTAS = TOTAL_VENTAS; }


    public PersonalModel() {}

    // getters & setters (generar/pegar desde tu IDE)...
    // ejemplo:
    public int getID_USER() { return ID_USER; }
    public void setID_USER(int ID_USER) { this.ID_USER = ID_USER; }

    public int getID_EMPRESA() { return ID_EMPRESA; }
    public void setID_EMPRESA(int ID_EMPRESA) { this.ID_EMPRESA = ID_EMPRESA; }

    public int getID_ROL() { return ID_ROL; }
    public void setID_ROL(int ID_ROL) { this.ID_ROL = ID_ROL; }

    public String getNOMBRE1() { return NOMBRE1; }
    public void setNOMBRE1(String NOMBRE1) { this.NOMBRE1 = NOMBRE1; }

    public String getNOMBRE2() { return NOMBRE2; }
    public void setNOMBRE2(String NOMBRE2) { this.NOMBRE2 = NOMBRE2; }

    public String getAPELLIDO1() { return APELLIDO1; }
    public void setAPELLIDO1(String APELLIDO1) { this.APELLIDO1 = APELLIDO1; }

    public String getAPELLIDO2() { return APELLIDO2; }
    public void setAPELLIDO2(String APELLIDO2) { this.APELLIDO2 = APELLIDO2; }

    public String getCEDULA() { return CEDULA; }
    public void setCEDULA(String CEDULA) { this.CEDULA = CEDULA; }

    public String getCORREO() { return CORREO; }
    public void setCORREO(String CORREO) { this.CORREO = CORREO; }

    public String getCONTRASENA_HASH() { return CONTRASENA_HASH; }
    public void setCONTRASENA_HASH(String CONTRASENA_HASH) { this.CONTRASENA_HASH = CONTRASENA_HASH; }

    public Date getFECHA_INGRESO() { return FECHA_INGRESO; }
    public void setFECHA_INGRESO(Date FECHA_INGRESO) { this.FECHA_INGRESO = FECHA_INGRESO; }

    public double getSALARIO() { return SALARIO; }
    public void setSALARIO(double SALARIO) { this.SALARIO = SALARIO; }

    public String getTIPO_CONTRATO() { return TIPO_CONTRATO; }
    public void setTIPO_CONTRATO(String TIPO_CONTRATO) { this.TIPO_CONTRATO = TIPO_CONTRATO; }

    public boolean isESTADO() { return ESTADO; }
    public void setESTADO(boolean ESTADO) { this.ESTADO = ESTADO; }

    public boolean isES_VENDEDOR() { return ES_VENDEDOR; }
    public void setES_VENDEDOR(boolean ES_VENDEDOR) { this.ES_VENDEDOR = ES_VENDEDOR; }

    public String getCODIGO_VENDEDOR() { return CODIGO_VENDEDOR; }
    public void setCODIGO_VENDEDOR(String CODIGO_VENDEDOR) { this.CODIGO_VENDEDOR = CODIGO_VENDEDOR; }

    public String getCODIGO_DISTRITO() { return CODIGO_DISTRITO; }
    public void setCODIGO_DISTRITO(String CODIGO_DISTRITO) { this.CODIGO_DISTRITO = CODIGO_DISTRITO; }

    public Date getFECHA_INGRESO_VENDEDOR() { return FECHA_INGRESO_VENDEDOR; }
    public void setFECHA_INGRESO_VENDEDOR(Date FECHA_INGRESO_VENDEDOR) { this.FECHA_INGRESO_VENDEDOR = FECHA_INGRESO_VENDEDOR; }

    public String getTIPO_VENDEDOR() { return TIPO_VENDEDOR; }
    public void setTIPO_VENDEDOR(String TIPO_VENDEDOR) { this.TIPO_VENDEDOR = TIPO_VENDEDOR; }
}
