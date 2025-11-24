package com.bodegapp.roles.service;

import com.bodegapp.roles.dao.RolDAO;
import com.bodegapp.roles.model.RolModel;
import java.util.List;

public class RolService {
    
     private final RolDAO dao = new RolDAO();

    public List<RolModel> obtenerRoles(int idEmpresa) {
        return dao.listar(idEmpresa);
    }

    public boolean registrarRol(RolModel rol) {
        return dao.registrar(rol);
    }
    
}
