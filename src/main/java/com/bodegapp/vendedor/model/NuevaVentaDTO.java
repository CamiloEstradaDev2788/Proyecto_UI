package com.bodegapp.vendedor.model;

import java.sql.Date;

public class NuevaVentaDTO {
    
  private String NUMERO_FACTURA;
private String CODIGO_VENDEDOR;
private String CODIGO_CLIENTE;
private Date FECHA_FACTURA;
private Date FECHA_CANCELACION;
private String ESTADO_FACTURA;
private double PROCENTAJE_IGV;


    public NuevaVentaDTO() {
    }

    public NuevaVentaDTO(String NUMERO_FACTURA, String CODIGO_VENDEDOR, String CODIGO_CLIENTE, Date FECHA_FACTURA, Date FECHA_CANCELACION, String ESTADO_FACTURA, double PROCENTAJE_IGV) {
        this.NUMERO_FACTURA = NUMERO_FACTURA;
        this.CODIGO_VENDEDOR = CODIGO_VENDEDOR;
        this.CODIGO_CLIENTE = CODIGO_CLIENTE;
        this.FECHA_FACTURA = FECHA_FACTURA;
        this.FECHA_CANCELACION = FECHA_CANCELACION;
        this.ESTADO_FACTURA = ESTADO_FACTURA;
        this.PROCENTAJE_IGV = PROCENTAJE_IGV;
    }

    public String getNUMERO_FACTURA() {
        return NUMERO_FACTURA;
    }

    public void setNUMERO_FACTURA(String NUMERO_FACTURA) {
        this.NUMERO_FACTURA = NUMERO_FACTURA;
    }

    public String getCODIGO_VENDEDOR() {
        return CODIGO_VENDEDOR;
    }

    public void setCODIGO_VENDEDOR(String CODIGO_VENDEDOR) {
        this.CODIGO_VENDEDOR = CODIGO_VENDEDOR;
    }

    public String getCODIGO_CLIENTE() {
        return CODIGO_CLIENTE;
    }

    public void setCODIGO_CLIENTE(String CODIGO_CLIENTE) {
        this.CODIGO_CLIENTE = CODIGO_CLIENTE;
    }

    public Date getFECHA_FACTURA() {
        return FECHA_FACTURA;
    }

    public void setFECHA_FACTURA(Date FECHA_FACTURA) {
        this.FECHA_FACTURA = FECHA_FACTURA;
    }

    public Date getFECHA_CANCELACION() {
        return FECHA_CANCELACION;
    }

    public void setFECHA_CANCELACION(Date FECHA_CANCELACION) {
        this.FECHA_CANCELACION = FECHA_CANCELACION;
    }

    public String getESTADO_FACTURA() {
        return ESTADO_FACTURA;
    }

    public void setESTADO_FACTURA(String ESTADO_FACTURA) {
        this.ESTADO_FACTURA = ESTADO_FACTURA;
    }

    public double getPROCENTAJE_IGV() {
        return PROCENTAJE_IGV;
    }

    public void setPROCENTAJE_IGV(double PROCENTAJE_IGV) {
        this.PROCENTAJE_IGV = PROCENTAJE_IGV;
    }
    
}
