package com.bodegapp.rol.dao;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.rol.model.RolModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {
    
    public List<RolModel> listar(){
    
    List<RolModel> lista = new ArrayList<>();
    String SQL = "SELECT * FROM roles";
    
    try(Connection con = ConexionBD.getConexion();
        PreparedStatement ps = con.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery()){
    
            while(rs.next()){
                RolModel rol = new RolModel();
                rol.setID_ROL(rs.getInt("id_rol"));
                rol.setID_EMPRESA(rs.getInt("id_empresa"));
                rol.setNOMBRE_ROL(rs.getString("nombre_rol"));
                rol.setDESCRIPCION(rs.getString("descripcion"));
                lista.add(rol);
            
            }
    }catch(SQLException e){
    
        System.out.println("Error al listar los roles: " + e.getMessage());
        
    }
        return lista;
    }
    
    public boolean registrar(RolModel rol){
    
        String SQL = "INSERT INTO roles (ID_EMPRESA, NOMBRE_ROL, DESCRIPCION) values (?, ?, ?)";
        try(Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(SQL)){
            
            ps.setInt(1,rol.getID_EMPRESA());
            ps.setString(2, rol.getNOMBRE_ROL());
            ps.setString(3, rol.getDESCRIPCION());
            ps.executeUpdate();
            return true;
            
        }catch(SQLException e){
            System.out.println("Error al registrar el rol: " + e.getMessage());
            return false;
        }
          
    }  
    
}
