package com.bodegapp.inventario.service;

import java.util.List;

import com.bodegapp.inventario.dao.InventarioDAO;
import com.bodegapp.inventario.model.InventarioModel;
import com.bodegapp.proveedor.model.ProveedorModel;

public class InventarioService {

    private InventarioDAO dao = new InventarioDAO();

    // Listar productos por empresa
    public List<InventarioModel> listar(int idEmpresa) {
        return dao.listar(idEmpresa);
    }

    // Buscar productos por criterio
    public List<InventarioModel> buscar(String criterio) {
        return dao.buscar(criterio);
    }

    // Registrar nuevo producto
    public boolean registrar(InventarioModel p, ProveedorModel prv) {
        return dao.registrar(p, prv);
    }

    // Obtener producto por código
    public InventarioModel obtenerProducto(String codigoProducto) {
        return dao.obtenerProducto(codigoProducto);
    }

    // Actualizar producto
    public boolean actualizarProducto(InventarioModel p) {
        return dao.actualizarProducto(p);
    }

    // Eliminar producto
    public boolean eliminarProducto(String codigoProducto) {
        return dao.eliminarProducto(codigoProducto);
    }
}
