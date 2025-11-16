package com.bodegapp.login.service;

import com.bodegapp.login.model.LoginModel;
import com.bodegapp.usuarios.dao.UsuarioDAO;
import com.bodegapp.usuarios.model.UsuarioModel;

public class LoginService {
    
    private UsuarioDAO dao = new UsuarioDAO();

    public UsuarioModel autenticar(LoginModel login){

        if(login.getCorreo() == null || login.getCorreo().isEmpty()){
            return null;
        }
        if(login.getContrasena() == null || login.getContrasena().isEmpty()){
            return null;
        }

    return dao.login(login.getCorreo(), login.getContrasena());

    }

}
