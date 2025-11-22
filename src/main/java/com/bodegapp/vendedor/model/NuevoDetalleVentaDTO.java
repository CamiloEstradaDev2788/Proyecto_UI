package com.bodegapp.vendedor.model;


public class NuevoDetalleVentaDTO {
    
    private String CODIGO_FACTURA;
    private String CODIGO_PRODUCTO;
    private int CANTIDAD_VENTA;
    private double PRECIO_VENTA;

    public NuevoDetalleVentaDTO() {
    }

    public NuevoDetalleVentaDTO(String CODIGO_FACTURA, String CODIGO_PRODUCTO, int CANTIDAD_VENTA, double PRECIO_VENTA) {
        this.CODIGO_FACTURA = CODIGO_FACTURA;
        this.CODIGO_PRODUCTO = CODIGO_PRODUCTO;
        this.CANTIDAD_VENTA = CANTIDAD_VENTA;
        this.PRECIO_VENTA = PRECIO_VENTA;
    }

    public String getCODIGO_FACTURA() {
        return CODIGO_FACTURA;
    }

    public void setCODIGO_FACTURA(String CODIGO_FACTURA) {
        this.CODIGO_FACTURA = CODIGO_FACTURA;
    }

    public String getCODIGO_PRODUCTO() {
        return CODIGO_PRODUCTO;
    }

    public void setCODIGO_PRODUCTO(String CODIGO_PRODUCTO) {
        this.CODIGO_PRODUCTO = CODIGO_PRODUCTO;
    }

    public int getCANTIDAD_VENTA() {
        return CANTIDAD_VENTA;
    }

    public void setCANTIDAD_VENTA(int CANTIDAD_VENTA) {
        this.CANTIDAD_VENTA = CANTIDAD_VENTA;
    }

    public double getPRECIO_VENTA() {
        return PRECIO_VENTA;
    }

    public void setPRECIO_VENTA(double PRECIO_VENTA) {
        this.PRECIO_VENTA = PRECIO_VENTA;
    }
    
}
