package com.bodegapp.inventario.service;

import java.util.List;
import com.bodegapp.inventario.dao.InventarioDAO;
import com.bodegapp.inventario.model.InventarioModel;
import com.bodegapp.usuarios.model.UsuarioModel;

public class InventarioService {
    InventarioDAO dao = new InventarioDAO();

    public List<InventarioModel> listar(int idEmpresa1) {
        return dao.listar(idEmpresa1);
    }

    public boolean registrar(InventarioModel p) {
        return dao.registrar(p);
    }

    public List<InventarioModel> buscar(String criterio) {
        return dao.buscar(criterio);
    }
}
