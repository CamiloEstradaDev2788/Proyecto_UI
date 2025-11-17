package com.bodegapp.personal.dao;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.personal.model.PersonalModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalDAO {
    
    public List<PersonalModel> listar(int idEmpresa) {
        List<PersonalModel> lista = new ArrayList<>();
        
        String sql = "SELECT u.ID_USER, " +
                     "       CONCAT(u.NOMBRE1, ' ', COALESCE(u.NOMBRE2, ''), ' ', u.APELLIDO1, ' ', COALESCE(u.APELLIDO2, '')) AS NOMBRE_COMPLETO, " +
                     "       COALESCE(r.NOMBRE_ROL, 'Sin cargo') AS CARGO, " +
                     "       u.SALARIO, " +
                     "       u.TIPO_CONTRATO, " +
                     "       u.ESTADO, " +
                     "       u.CORREO, " +
                     "       u.CEDULA, " +
                     "       CASE WHEN v.CODIGO_VENDEDOR IS NOT NULL THEN 1 ELSE 0 END AS ES_VENDEDOR, " +
                     "       v.CODIGO_VENDEDOR " +
                     "FROM usuarios u " +
                     "LEFT JOIN roles r ON r.ID_ROL = u.ID_ROL " +
                     "LEFT JOIN vendedores v ON v.ID_USER = u.ID_USER " +
                     "WHERE u.ID_EMPRESA = ? " +
                     "ORDER BY u.NOMBRE1, u.APELLIDO1";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idEmpresa);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PersonalModel p = new PersonalModel();
                    
                    p.setID_USER(rs.getInt("ID_USER"));
                    p.setNOMBRE_COMPLETO(rs.getString("NOMBRE_COMPLETO"));
                    p.setCARGO(rs.getString("CARGO"));
                    p.setSALARIO(rs.getDouble("SALARIO"));
                    p.setTIPO_CONTRATO(rs.getString("TIPO_CONTRATO"));
                    p.setESTADO(rs.getBoolean("ESTADO"));
                    p.setCORREO(rs.getString("CORREO"));
                    p.setCEDULA(rs.getString("CEDULA"));
                    p.setES_VENDEDOR(rs.getInt("ES_VENDEDOR") == 1);
                    p.setCODIGO_VENDEDOR(rs.getString("CODIGO_VENDEDOR"));
                    
                    lista.add(p);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error al listar personal: " + e.getMessage());
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public PersonalModel obtenerPorId(int idUser) {
        PersonalModel personal = null;
        
        String sql = "SELECT u.ID_USER, " +
                     "       CONCAT(u.NOMBRE1, ' ', COALESCE(u.NOMBRE2, ''), ' ', u.APELLIDO1, ' ', COALESCE(u.APELLIDO2, '')) AS NOMBRE_COMPLETO, " +
                     "       COALESCE(r.NOMBRE_ROL, 'Sin cargo') AS CARGO, " +
                     "       u.SALARIO, " +
                     "       u.TIPO_CONTRATO, " +
                     "       u.ESTADO, " +
                     "       u.CORREO, " +
                     "       u.CEDULA, " +
                     "       CASE WHEN v.CODIGO_VENDEDOR IS NOT NULL THEN 1 ELSE 0 END AS ES_VENDEDOR, " +
                     "       v.CODIGO_VENDEDOR " +
                     "FROM usuarios u " +
                     "LEFT JOIN roles r ON r.ID_ROL = u.ID_ROL " +
                     "LEFT JOIN vendedores v ON v.ID_USER = u.ID_USER " +
                     "WHERE u.ID_USER = ?";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idUser);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    personal = new PersonalModel();
                    
                    personal.setID_USER(rs.getInt("ID_USER"));
                    personal.setNOMBRE_COMPLETO(rs.getString("NOMBRE_COMPLETO"));
                    personal.setCARGO(rs.getString("CARGO"));
                    personal.setSALARIO(rs.getDouble("SALARIO"));
                    personal.setTIPO_CONTRATO(rs.getString("TIPO_CONTRATO"));
                    personal.setESTADO(rs.getBoolean("ESTADO"));
                    personal.setCORREO(rs.getString("CORREO"));
                    personal.setCEDULA(rs.getString("CEDULA"));
                    personal.setES_VENDEDOR(rs.getInt("ES_VENDEDOR") == 1);
                    personal.setCODIGO_VENDEDOR(rs.getString("CODIGO_VENDEDOR"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error al obtener personal: " + e.getMessage());
            e.printStackTrace();
        }
        
        return personal;
    }
}

