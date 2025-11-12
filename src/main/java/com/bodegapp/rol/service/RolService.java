package com.bodegapp.rol.service;

import com.bodegapp.rol.dao.RolDAO;
import com.bodegapp.rol.model.RolModel;
import java.util.List;

public class RolService {
    
     private final RolDAO dao = new RolDAO();

    public List<RolModel> obtenerRoles() {
        return dao.listar();
    }

    public boolean registrarRol(RolModel rol) {
        return dao.registrar(rol);
    }
    
}
