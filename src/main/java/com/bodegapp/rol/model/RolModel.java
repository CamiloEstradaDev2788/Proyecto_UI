package com.bodegapp.rol.model;

public class RolModel {

    private int ID_ROL;
    private int ID_EMPRESA;
    private String NOMBRE_ROL;
    private String DESCRIPCION;
    
    
    public RolModel(int ID_EMPRESA, String NOMBRE_ROL, String DESCRIPCION) {
        this.ID_EMPRESA = ID_EMPRESA;
        this.NOMBRE_ROL = NOMBRE_ROL;
        this.DESCRIPCION = DESCRIPCION;
    }

    public RolModel() {
    }

    public int getID_ROL() {
        return ID_ROL;
    }

    public void setID_ROL(int ID_ROL) {
        this.ID_ROL = ID_ROL;
    }

    public int getID_EMPRESA() {
        return ID_EMPRESA;
    }

    public void setID_EMPRESA(int ID_EMPRESA) {
        this.ID_EMPRESA = ID_EMPRESA;
    }

    public String getNOMBRE_ROL() {
        return NOMBRE_ROL;
    }

    public void setNOMBRE_ROL(String NOMBRE_ROL) {
        this.NOMBRE_ROL = NOMBRE_ROL;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }
    

    
}
