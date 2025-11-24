package com.bodegapp.distrito.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bodegapp.distrito.model.DistritoModel;
import com.bodegapp.core.database.ConexionBD;

public class DistritoDAO {

    private Connection conn;

    public DistritoDAO() throws SQLException {
        this.conn = ConexionBD.getConexion();
    }

    public List<DistritoModel> listarDistritos() throws SQLException {
        List<DistritoModel> lista = new ArrayList<>();
        String sql = "SELECT CODIGO_DISTRITO, NOMBRE_DISTRITO FROM distritos";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new DistritoModel(rs.getString("CODIGO_DISTRITO"), rs.getString("NOMBRE_DISTRITO")));
        }
        rs.close();
        ps.close();
        return lista;
    }

    public DistritoModel obtenerDistrito(String codigo) throws SQLException {
        DistritoModel distrito = null;
        String sql = "SELECT CODIGO_DISTRITO, NOMBRE_DISTRITO FROM distritos WHERE CODIGO_DISTRITO=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, codigo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            distrito = new DistritoModel(rs.getString("CODIGO_DISTRITO"), rs.getString("NOMBRE_DISTRITO"));
        }
        rs.close();
        ps.close();
        return distrito;
    }

    public boolean agregarDistrito(DistritoModel distrito) throws SQLException {
        String sql = "INSERT INTO distritos (CODIGO_DISTRITO, NOMBRE_DISTRITO) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, distrito.getCodigoDistrito());
        ps.setString(2, distrito.getNombreDistrito());
        int filas = ps.executeUpdate();
        ps.close();
        return filas > 0;
    }

    public boolean actualizarDistrito(DistritoModel distrito) throws SQLException {
        String sql = "UPDATE distritos SET NOMBRE_DISTRITO=? WHERE CODIGO_DISTRITO=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, distrito.getNombreDistrito());
        ps.setString(2, distrito.getCodigoDistrito());
        int filas = ps.executeUpdate();
        ps.close();
        return filas > 0;
    }

    public boolean eliminarDistrito(String codigo) throws SQLException {
        String sql = "DELETE FROM distritos WHERE CODIGO_DISTRITO=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, codigo);
        int filas = ps.executeUpdate();
        ps.close();
        return filas > 0;
    }
}
