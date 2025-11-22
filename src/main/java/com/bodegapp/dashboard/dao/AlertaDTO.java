package com.bodegapp.dashboard.dao;

public class AlertaDTO {

    private String titulo;
    private String descripcion;
    private String fecha;
    private String vendedor;
    private String solicitud;

    public AlertaDTO(String titulo, String descripcion, String fecha, String vendedor, String solicitud) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.vendedor = vendedor;
        this.solicitud = solicitud;
    }

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getFecha() { return fecha; }
    public String getVendedor() { return vendedor; }
    public String getSolicitud() { return solicitud; }
}
