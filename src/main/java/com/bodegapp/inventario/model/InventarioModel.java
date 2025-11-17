package com.bodegapp.inventario.model;

public class InventarioModel {

    private String CODIGO_PRODUCTO;
    private String DESCRIPCION_PRODUCTO;
    private double PRECIO_PRODUCTO;
    private int SACO_PRODUCTO;
    private int MINIMO_STOCK;
    private String UNIDAD_PRODUCTO;
    private String LINEA_PRODUCTO;
    private String IMPUESTO_PRODUCTO;
    private String NOMBRE_PROVEEDOR;

    public String getCODIGO_PRODUCTO() { return CODIGO_PRODUCTO; }
    public void setCODIGO_PRODUCTO(String c) { this.CODIGO_PRODUCTO = c; }

    public String getDESCRIPCION_PRODUCTO() { return DESCRIPCION_PRODUCTO; }
    public void setDESCRIPCION_PRODUCTO(String d) { this.DESCRIPCION_PRODUCTO = d; }

    public double getPRECIO_PRODUCTO() { return PRECIO_PRODUCTO; }
    public void setPRECIO_PRODUCTO(double p) { this.PRECIO_PRODUCTO = p; }

    public int getSACO_PRODUCTO() { return SACO_PRODUCTO; }
    public void setSACO_PRODUCTO(int s) { this.SACO_PRODUCTO = s; }

    public int getMINIMO_STOCK() { return MINIMO_STOCK; }
    public void setMINIMO_STOCK(int m) { this.MINIMO_STOCK = m; }

    public String getUNIDAD_PRODUCTO() { return UNIDAD_PRODUCTO; }
    public void setUNIDAD_PRODUCTO(String u) { this.UNIDAD_PRODUCTO = u; }

    public String getLINEA_PRODUCTO() { return LINEA_PRODUCTO; }
    public void setLINEA_PRODUCTO(String l) { this.LINEA_PRODUCTO = l; }

    public String getIMPUESTO_PRODUCTO() { return IMPUESTO_PRODUCTO; }
    public void setIMPUESTO_PRODUCTO(String i) { this.IMPUESTO_PRODUCTO = i; }

    public String getNOMBRE_PROVEEDOR() { return NOMBRE_PROVEEDOR; }
    public void setNOMBRE_PROVEEDOR(String n) { this.NOMBRE_PROVEEDOR = n; }
}
