package com.bodegapp.inventario.model;

public class ProductoModel {
    
    private String CODIGO_PRODUCTO;
    private String DESCRIPCION_PRODUCTO;
    private double PRECIO_PRODUCTO;
    private int SACO_PRODUCTO;
    private int MINIMO_STOCK;
    private String UNIDAD_PRODUCTO;
    private String LINEA_PRODUCTO;
    private String IMPUESTO_PRODUCTO;

    public ProductoModel(String CODIGO_PRODUCTO, String DESCRIPCION_PRODUCTO, double PRECIO_PRODUCTO, int SACO_PRODUCTO, int MINIMO_STOCK, String UNIDAD_PRODUCTO, String LINEA_PRODUCTO, String IMPUESTO_PRODUCTO) {
        this.CODIGO_PRODUCTO = CODIGO_PRODUCTO;
        this.DESCRIPCION_PRODUCTO = DESCRIPCION_PRODUCTO;
        this.PRECIO_PRODUCTO = PRECIO_PRODUCTO;
        this.SACO_PRODUCTO = SACO_PRODUCTO;
        this.MINIMO_STOCK = MINIMO_STOCK;
        this.UNIDAD_PRODUCTO = UNIDAD_PRODUCTO;
        this.LINEA_PRODUCTO = LINEA_PRODUCTO;
        this.IMPUESTO_PRODUCTO = IMPUESTO_PRODUCTO;
    }

    public ProductoModel() {
    }

    public String getCODIGO_PRODUCTO() {
        return CODIGO_PRODUCTO;
    }

    public void setCODIGO_PRODUCTO(String CODIGO_PRODUCTO) {
        this.CODIGO_PRODUCTO = CODIGO_PRODUCTO;
    }

    public String getDESCRIPCION_PRODUCTO() {
        return DESCRIPCION_PRODUCTO;
    }

    public void setDESCRIPCION_PRODUCTO(String DESCRIPCION_PRODUCTO) {
        this.DESCRIPCION_PRODUCTO = DESCRIPCION_PRODUCTO;
    }

    public double getPRECIO_PRODUCTO() {
        return PRECIO_PRODUCTO;
    }

    public void setPRECIO_PRODUCTO(double PRECIO_PRODUCTO) {
        this.PRECIO_PRODUCTO = PRECIO_PRODUCTO;
    }

    public int getSACO_PRODUCTO() {
        return SACO_PRODUCTO;
    }

    public void setSACO_PRODUCTO(int SACO_PRODUCTO) {
        this.SACO_PRODUCTO = SACO_PRODUCTO;
    }

    public int getMINIMO_STOCK() {
        return MINIMO_STOCK;
    }

    public void setMINIMO_STOCK(int MINIMO_STOCK) {
        this.MINIMO_STOCK = MINIMO_STOCK;
    }

    public String getUNIDAD_PRODUCTO() {
        return UNIDAD_PRODUCTO;
    }

    public void setUNIDAD_PRODUCTO(String UNIDAD_PRODUCTO) {
        this.UNIDAD_PRODUCTO = UNIDAD_PRODUCTO;
    }

    public String getLINEA_PRODUCTO() {
        return LINEA_PRODUCTO;
    }

    public void setLINEA_PRODUCTO(String LINEA_PRODUCTO) {
        this.LINEA_PRODUCTO = LINEA_PRODUCTO;
    }

    public String getIMPUESTO_PRODUCTO() {
        return IMPUESTO_PRODUCTO;
    }

    public void setIMPUESTO_PRODUCTO(String IMPUESTO_PRODUCTO) {
        this.IMPUESTO_PRODUCTO = IMPUESTO_PRODUCTO;
    }
    
    
    
}
