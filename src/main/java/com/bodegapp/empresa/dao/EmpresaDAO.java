package com.bodegapp.empresa.dao;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.empresa.model.EmpresaModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {
   
    public boolean registrar (EmpresaModel e){
    
        String SQL = "INSERT INTO empresas (NOMBRE, NIT, DIRECCION, TELEFONO, ESTADO, LOGO) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection con = ConexionBD.getConexion();
            PreparedStatement  ps = con.prepareStatement(SQL)){
            
            ps.setString(1, e.getNOMBRE());
            ps.setString(2, e.getNIT());
            ps.setString(3, e.getDIRECCION());
            ps.setString(4, e.getTELEFONO());
            ps.setBoolean(5, e.isESTADO());
            ps.setString(6, e.getLOGO());
            ps.executeUpdate();
            return true;
        }catch(SQLException ex){
        
            System.out.println("Error al registrar la empresa: " + ex.getMessage());
            return false;
        }
    
    }
    
    public List<EmpresaModel> listar() {
        List<EmpresaModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM empresas";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EmpresaModel e = new EmpresaModel();
                e.setID_EMPRESA(rs.getInt("id_empresa"));
                e.setNIT(rs.getString("nit"));
                e.setNOMBRE(rs.getString("nombre"));
                e.setDIRECCION(rs.getString("direccion"));
                e.setTELEFONO(rs.getString("telefono"));
                e.setESTADO(rs.getBoolean("estado"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("⚠️ Error al listar empresas: " + ex.getMessage());
        }
        return lista;
    }
    
}
