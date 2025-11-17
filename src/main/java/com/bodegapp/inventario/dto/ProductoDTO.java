package com.bodegapp.inventario.dto;

public class ProductoDTO {

    private String codigoProducto;
    private String descripcion;
    private int cantidad;
    private double precio;
    private String linea;
    private String fechaEntrega;

    public ProductoDTO() {}

    public ProductoDTO(String codigoProducto, String descripcion, int cantidad,
                               double precio, String lineaProducto, String fechaEntrega) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.linea = lineaProducto;
        this.fechaEntrega = fechaEntrega;
    }

    public String getCodigoProducto() { return codigoProducto; }
    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }
    public String getLinea() { return linea; }
    public String getFechaEntrega() { return fechaEntrega; }

}
