package com.bodegapp.vendedor.service;

import com.bodegapp.vendedor.dao.VendedorDAO;
import com.bodegapp.vendedor.model.VentaRecienteModel;

import java.util.List;

public class DashboardVendedorService {

    private VendedorDAO dao = new VendedorDAO();

    public double obtenerTotalVentas(String codigoVendedor) {
        return dao.obtenerTotalVentas(codigoVendedor);
    }

    public int obtenerTotalProductosVendidos(String codigoVendedor) {
        return dao.obtenerTotalProductosVendidos(codigoVendedor);
    }

    public int obtenerTotalClientesAtendidos(String codigoVendedor) {
        return dao.obtenerTotalClientesAtendidos(codigoVendedor);
    }

    public List<VentaRecienteModel> obtenerVentasRecientes(String codigoVendedor) {
        return dao.obtenerVentasRecientes(codigoVendedor);
    }
}
