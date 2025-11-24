package com.bodegapp.distrito.service;

import java.sql.SQLException;
import java.util.List;

import com.bodegapp.distrito.dao.DistritoDAO;
import com.bodegapp.distrito.model.DistritoModel;

public class DistritoService {

    private DistritoDAO dao;

    public DistritoService() throws SQLException {
        this.dao = new DistritoDAO();
    }

    public List<DistritoModel> listarDistritos() throws SQLException {
        return dao.listarDistritos();
    }

    public DistritoModel obtenerDistrito(String codigo) throws SQLException {
        return dao.obtenerDistrito(codigo);
    }

    public boolean agregarDistrito(DistritoModel distrito) throws SQLException {
        return dao.agregarDistrito(distrito);
    }

    public boolean actualizarDistrito(DistritoModel distrito) throws SQLException {
        return dao.actualizarDistrito(distrito);
    }

    public boolean eliminarDistrito(String codigo) throws SQLException {
        return dao.eliminarDistrito(codigo);
    }
}
