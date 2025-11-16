package com.bodegapp.usuarios.dao;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.usuarios.model.UsuarioModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
    
    public boolean registrar(UsuarioModel u){
    
    String SQL = "INSERT INTO usuarios (ID_ROL, ID_EMPRESA, NOMBRE1, NOMBRE2, APELLIDO1, APELLIDO2, CEDULA, CORREO, CONTRASENA_HASH, FECHA_INGRESO, SALARIO, TIPO_CONTRATO, ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try(Connection con = ConexionBD.getConexion();
        PreparedStatement ps = con.prepareStatement(SQL)){
    
    ps.setInt(1, u.getID_ROL());
    ps.setInt(2, u.getID_EMPRESA());
    ps.setString(3, u.getNOMBRE1());
    ps.setString(4, u.getNOMBRE2());
    ps.setString(5, u.getAPELLIDO1());
    ps.setString(6, u.getAPELLIDO2());
    ps.setString(7, u.getCEDULA());
    ps.setString(8, u.getCORREO());
    ps.setString(9, u.getCONTRASENA_HASH());
    ps.setDate(10, new java.sql.Date(u.getFECHA_INGRESO().getTime()));
    ps.setDouble(11, u.getSALARIO());
    ps.setString(12, u.getTIPO_CONTRATO());
    ps.setBoolean(13, u.isESTADO());
    ps.executeUpdate();
    return true;
    }catch(SQLException ex){
    
    System.out.println("Error al registrar al usuario: " + ex.getMessage());
    return false;
    }
    }
    
    public List <UsuarioModel> listar(){
    
        List<UsuarioModel> lista = new ArrayList<>();
        String SQL = "SELECT * FROM usuarios";
        try (Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery()){
        while(rs.next()){
            
            UsuarioModel u = new UsuarioModel();
            u.setID_USER(rs.getInt("ID_USER"));
            u.setID_EMPRESA(rs.getInt("id_empresa"));
            u.setID_ROL(rs.getInt("id_rol"));
            u.setNOMBRE1(rs.getString("nombre1"));
            u.setNOMBRE2(rs.getString("nombre2"));
            u.setAPELLIDO1(rs.getString("apellido1"));
            u.setAPELLIDO2(rs.getString("apellido2"));
            u.setCEDULA(rs.getString("cedula"));
            u.setCORREO(rs.getString("correo"));
            u.setCONTRASENA_HASH(rs.getString("contrasena_hash"));
            u.setFECHA_INGRESO(rs.getDate("fecha_ingreso"));
            u.setSALARIO(rs.getDouble("salario"));
            u.setTIPO_CONTRATO(rs.getString("Tipo_contrato"));
            u.setESTADO(rs.getBoolean("estado"));
            lista.add(u);
        }
        } catch (SQLException ex){
        
            System.out.println("⚠️ Error al listar empresas: " + ex.getMessage());
            
        }
        
        return lista;
    }
    
    public UsuarioModel login (String correo, String contrasenaHash) {
        UsuarioModel usuario = null;
        String SQL = "SELECT * FROM usuarios WHERE CORREO = ? AND CONTRASENA_HASH = ? AND ESTADO = 1";
        
        try (Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(SQL)) {
            
            ps.setString(1, correo);
            ps.setString(2, contrasenaHash);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new UsuarioModel();
                    usuario.setID_USER(rs.getInt("ID_USER"));
                    usuario.setID_EMPRESA(rs.getInt("id_empresa"));
                    usuario.setID_ROL(rs.getInt("id_rol"));
                    usuario.setNOMBRE1(rs.getString("nombre1"));
                    usuario.setNOMBRE2(rs.getString("nombre2"));
                    usuario.setAPELLIDO1(rs.getString("apellido1"));
                    usuario.setAPELLIDO2(rs.getString("apellido2"));
                    usuario.setCEDULA(rs.getString("cedula"));
                    usuario.setCORREO(rs.getString("correo"));
                    usuario.setCONTRASENA_HASH(rs.getString("contrasena_hash"));
                    usuario.setFECHA_INGRESO(rs.getDate("fecha_ingreso"));
                    usuario.setSALARIO(rs.getDouble("salario"));
                    usuario.setTIPO_CONTRATO(rs.getString("Tipo_contrato"));
                    usuario.setESTADO(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("⚠️ Error during login: " + ex.getMessage());
        }
        
        return usuario;
    }

}
