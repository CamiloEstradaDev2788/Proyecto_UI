package com.bodegapp.inventario.service;

import java.util.List;
import com.bodegapp.inventario.dao.InventarioDAO;
import com.bodegapp.inventario.model.InventarioModel;
import com.bodegapp.usuarios.model.UsuarioModel;
import com.bodegapp.proveedor.model.ProveedorModel;

public class InventarioService {
    InventarioDAO dao = new InventarioDAO();

    public List<InventarioModel> listar(int idEmpresa1) {
        return dao.listar(idEmpresa1);
    }

    public boolean registrar(InventarioModel p, ProveedorModel prv) {
        return dao.registrar(p, prv);
    }

    public List<InventarioModel> buscar(String criterio) {
        return dao.buscar(criterio);
    }
}
