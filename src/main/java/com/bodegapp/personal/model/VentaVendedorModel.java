package com.bodegapp.personal.model;

import java.util.Date;

public class VentaVendedorModel {
    
    private String NUMERO_FACTURA;
    private Date FECHA_FACTURA;
    private double TOTAL_VENTA;
    private int CANTIDAD_PRODUCTOS;
    private String NOMBRE_CLIENTE;

    public VentaVendedorModel() {
    }

    public String getNUMERO_FACTURA() {
        return NUMERO_FACTURA;
    }

    public void setNUMERO_FACTURA(String NUMERO_FACTURA) {
        this.NUMERO_FACTURA = NUMERO_FACTURA;
    }

    public Date getFECHA_FACTURA() {
        return FECHA_FACTURA;
    }

    public void setFECHA_FACTURA(Date FECHA_FACTURA) {
        this.FECHA_FACTURA = FECHA_FACTURA;
    }

    public double getTOTAL_VENTA() {
        return TOTAL_VENTA;
    }

    public void setTOTAL_VENTA(double TOTAL_VENTA) {
        this.TOTAL_VENTA = TOTAL_VENTA;
    }

    public int getCANTIDAD_PRODUCTOS() {
        return CANTIDAD_PRODUCTOS;
    }

    public void setCANTIDAD_PRODUCTOS(int CANTIDAD_PRODUCTOS) {
        this.CANTIDAD_PRODUCTOS = CANTIDAD_PRODUCTOS;
    }

    public String getNOMBRE_CLIENTE() {
        return NOMBRE_CLIENTE;
    }

    public void setNOMBRE_CLIENTE(String NOMBRE_CLIENTE) {
        this.NOMBRE_CLIENTE = NOMBRE_CLIENTE;
    }
}

