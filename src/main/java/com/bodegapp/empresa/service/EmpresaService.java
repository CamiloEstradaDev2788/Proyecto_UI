package com.bodegapp.empresa.service;

import com.bodegapp.empresa.dao.EmpresaDAO;
import com.bodegapp.empresa.model.EmpresaModel;
import java.util.List;

public class EmpresaService {
    
    private final EmpresaDAO dao = new EmpresaDAO();
    
    public boolean registrarEmpresa(EmpresaModel e){
        return dao.registrar(e);
    }
    
    public List<EmpresaModel> obtenerEmpresas() {
        return dao.listar();
    }
    
}
