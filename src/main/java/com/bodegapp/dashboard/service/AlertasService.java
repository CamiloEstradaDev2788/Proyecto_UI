package com.bodegapp.dashboard.service;

import com.bodegapp.dashboard.dao.AlertasDAO;
import com.bodegapp.dashboard.dao.AlertaDTO;

import java.util.List;

public class AlertasService {

    private AlertasDAO dao = new AlertasDAO();

    public List<AlertaDTO> obtenerAlertas(int idEmpresa) {
        return dao.obtenerAlertas(idEmpresa);
    }
}
