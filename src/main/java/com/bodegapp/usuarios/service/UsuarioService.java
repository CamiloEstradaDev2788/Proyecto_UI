package com.bodegapp.usuarios.service;

import com.bodegapp.usuarios.dao.UsuarioDAO;
import com.bodegapp.usuarios.model.UsuarioModel;
import java.util.List;

public class UsuarioService {
    
    private final UsuarioDAO  dao = new UsuarioDAO();
    
    public boolean registrarUsuario(UsuarioModel u){
    return dao.registrar(u);
    }
    
    public List<UsuarioModel> obtenerUsuarios(){
    return dao.listar();
    }
}
