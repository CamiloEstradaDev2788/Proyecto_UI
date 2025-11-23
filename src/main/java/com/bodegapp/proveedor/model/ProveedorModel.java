package com.bodegapp.proveedor.model;

public class ProveedorModel {

    private String CODIGO_PROVEEDOR;
    private String RAZON_SOCIAL;
    private String DIRECCION_PROVEEDOR;
    private String TELEFONO_PROVEEDOR;
    private String REPRESENTANTE_PROVEEDOR;
    private String CODIGO_DISTRITO;
    private int ID_EMPRESA;
    
    public ProveedorModel() {
    }

    public ProveedorModel(String CODIGO_PROVEEDOR, String RAZON_SOCIAL, String DIRECCION_PROVEEDOR, String TELEFONO_PROVEEDOR, String REPRESENTANTE_PROVEEDOR, String CODIGO_DISTRITO, int ID_EMPRESA) {
        this.CODIGO_PROVEEDOR = CODIGO_PROVEEDOR;
        this.RAZON_SOCIAL = RAZON_SOCIAL;
        this.DIRECCION_PROVEEDOR = DIRECCION_PROVEEDOR;
        this.TELEFONO_PROVEEDOR = TELEFONO_PROVEEDOR;
        this.REPRESENTANTE_PROVEEDOR = REPRESENTANTE_PROVEEDOR;
        this.CODIGO_DISTRITO = CODIGO_DISTRITO;
        this.ID_EMPRESA = ID_EMPRESA;
    }

    public String getCODIGO_PROVEEDOR() {
        return CODIGO_PROVEEDOR;
    }

    public void setCODIGO_PROVEEDOR(String CODIGO_PROVEEDOR) {
        this.CODIGO_PROVEEDOR = CODIGO_PROVEEDOR;
    }

    public String getRAZON_SOCIAL() {
        return RAZON_SOCIAL;
    }

    public void setRAZON_SOCIAL(String RAZON_SOCIAL) {
        this.RAZON_SOCIAL = RAZON_SOCIAL;
    }

    public String getDIRECCION_PROVEEDOR() {
        return DIRECCION_PROVEEDOR;
    }

    public void setDIRECCION_PROVEEDOR(String DIRECCION_PROVEEDOR) {
        this.DIRECCION_PROVEEDOR = DIRECCION_PROVEEDOR;
    }

    public String getTELEFONO_PROVEEDOR() {
        return TELEFONO_PROVEEDOR;
    }

    public void setTELEFONO_PROVEEDOR(String TELEFONO_PROVEEDOR) {
        this.TELEFONO_PROVEEDOR = TELEFONO_PROVEEDOR;
    }

    public String getREPRESENTANTE_PROVEEDOR() {
        return REPRESENTANTE_PROVEEDOR;
    }

    public void setREPRESENTANTE_PROVEEDOR(String REPRESENTANTE_PROVEEDOR) {
        this.REPRESENTANTE_PROVEEDOR = REPRESENTANTE_PROVEEDOR;
    }

    public String getCODIGO_DISTRITO() {
        return CODIGO_DISTRITO;
    }

    public void setCODIGO_DISTRITO(String CODIGO_DISTRITO) {
        this.CODIGO_DISTRITO = CODIGO_DISTRITO;
    }

    public int getID_EMPRESA() {
        return ID_EMPRESA;
    }

    public void setID_EMPRESA(int ID_EMPRESA) {
        this.ID_EMPRESA = ID_EMPRESA;
    }
    
    
    
}
