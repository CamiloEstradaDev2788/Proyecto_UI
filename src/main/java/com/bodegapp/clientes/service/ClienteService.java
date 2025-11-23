package com.bodegapp.clientes.service;

import java.util.List;
import com.bodegapp.clientes.dao.ClienteDAO;
import com.bodegapp.clientes.model.ClienteModel;

public class ClienteService {
    private ClienteDAO dao = new ClienteDAO();

    public List<ClienteModel> listar() {
        return dao.listar();
    }

    public ClienteModel obtener(String codigo) {
        return dao.obtenerPorCodigo(codigo);
    }

    public boolean registrar(ClienteModel c) {
        return dao.registrar(c);
    }

    public boolean actualizar(ClienteModel c) {
        return dao.actualizar(c);
    }

    public boolean eliminar(String codigo) {
        return dao.eliminar(codigo);
    }

    public List<ClienteModel> buscar(String criterio) {
        return dao.buscar(criterio);
    }
}
