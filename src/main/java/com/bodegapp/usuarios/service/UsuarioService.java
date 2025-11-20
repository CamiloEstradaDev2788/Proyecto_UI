package com.bodegapp.usuarios.service;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.usuarios.dao.UsuarioDAO;
import com.bodegapp.usuarios.model.UsuarioModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UsuarioService {
    
    private final UsuarioDAO  dao = new UsuarioDAO();
    
    public boolean registrarUsuario(UsuarioModel u){
    return dao.registrar(u);
    }
    
    public List<UsuarioModel> obtenerUsuarios(){
    return dao.listar();
    }
    
    public String obtenerCodigoVendedor(int idUsuario) {

    String sql = "SELECT CODIGO_VENDEDOR FROM vendedores WHERE ID_USER = ?";

    try (Connection con = ConexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idUsuario);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString("CODIGO_VENDEDOR");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

}
