package com.bodegapp.empresa.model;


public class EmpresaModel {
    
    private int ID_EMPRESA;
    private String NOMBRE;
    private String NIT;
    private String DIRECCION;
    private String TELEFONO;
    private boolean ESTADO;
    private String LOGO;

    public EmpresaModel() {
    }
    
    
    
    public EmpresaModel(String NOMBRE, String NIT, String DIRECCION, String TELEFONO, boolean ESTADO, String LOGO) {
        this.NOMBRE = NOMBRE;
        this.NIT = NIT;
        this.DIRECCION = DIRECCION;
        this.TELEFONO = TELEFONO;
        this.ESTADO = ESTADO;
        this.LOGO = LOGO;
    }

    public int getID_EMPRESA() {
        return ID_EMPRESA;
    }

    public void setID_EMPRESA(int ID_EMPRESA) {
        this.ID_EMPRESA = ID_EMPRESA;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public boolean isESTADO() {
        return ESTADO;
    }

    public void setESTADO(boolean ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getLOGO() {
        return LOGO;
    }

    public void setLOGO(String LOGO) {
        this.LOGO = LOGO;
    }
    
    
}
