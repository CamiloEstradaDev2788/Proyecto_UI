package com.bodegapp.clientes.dao;

import com.bodegapp.clientes.model.ClienteModel;
import com.bodegapp.core.database.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<ClienteModel> listar() {
        List<ClienteModel> lista = new ArrayList<>();
        String sql = "SELECT CODIGO_CLIENTE, NOMBRE_CLIENTE, DIRECCION_CLIENTE, TELEFONO_CLIENTE, RUC_CLIENTE, FECHA_REGISTRO, TIPO_CLIENTE, CONDICION_CLIENTE, CODIGO_DISTRITO FROM clientes";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ClienteModel c = new ClienteModel();
                c.setCODIGO_CLIENTE(rs.getString("CODIGO_CLIENTE"));
                c.setNOMBRE_CLIENTE(rs.getString("NOMBRE_CLIENTE"));
                c.setDIRECCION_CLIENTE(rs.getString("DIRECCION_CLIENTE"));
                c.setTELEFONO_CLIENTE(rs.getString("TELEFONO_CLIENTE"));
                c.setRUC_CLIENTE(rs.getString("RUC_CLIENTE"));
                c.setFECHA_REGISTRO(rs.getDate("FECHA_REGISTRO"));
                c.setTIPO_CLIENTE(rs.getString("TIPO_CLIENTE"));
                c.setCONDICION_CLIENTE(rs.getString("CONDICION_CLIENTE"));
                c.setCODIGO_DISTRITO(rs.getString("CODIGO_DISTRITO"));
                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ClienteModel obtenerPorCodigo(String codigo) {
        String sql = "SELECT * FROM clientes WHERE CODIGO_CLIENTE = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ClienteModel c = new ClienteModel();
                    c.setCODIGO_CLIENTE(rs.getString("CODIGO_CLIENTE"));
                    c.setNOMBRE_CLIENTE(rs.getString("NOMBRE_CLIENTE"));
                    c.setDIRECCION_CLIENTE(rs.getString("DIRECCION_CLIENTE"));
                    c.setTELEFONO_CLIENTE(rs.getString("TELEFONO_CLIENTE"));
                    c.setRUC_CLIENTE(rs.getString("RUC_CLIENTE"));
                    c.setFECHA_REGISTRO(rs.getDate("FECHA_REGISTRO"));
                    c.setTIPO_CLIENTE(rs.getString("TIPO_CLIENTE"));
                    c.setCONDICION_CLIENTE(rs.getString("CONDICION_CLIENTE"));
                    c.setCODIGO_DISTRITO(rs.getString("CODIGO_DISTRITO"));
                    return c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registrar(ClienteModel c) {
        String sql = "INSERT INTO clientes (CODIGO_CLIENTE, NOMBRE_CLIENTE, DIRECCION_CLIENTE, TELEFONO_CLIENTE, RUC_CLIENTE, FECHA_REGISTRO, TIPO_CLIENTE, CONDICION_CLIENTE, CODIGO_DISTRITO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCODIGO_CLIENTE());
            ps.setString(2, c.getNOMBRE_CLIENTE());
            ps.setString(3, c.getDIRECCION_CLIENTE());
            ps.setString(4, c.getTELEFONO_CLIENTE());
            ps.setString(5, c.getRUC_CLIENTE());
            ps.setDate(6, c.getFECHA_REGISTRO());
            ps.setString(7, c.getTIPO_CLIENTE());
            ps.setString(8, c.getCONDICION_CLIENTE());
            ps.setString(9, c.getCODIGO_DISTRITO());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(ClienteModel c) {
        String sql = "UPDATE clientes SET NOMBRE_CLIENTE=?, DIRECCION_CLIENTE=?, TELEFONO_CLIENTE=?, RUC_CLIENTE=?, FECHA_REGISTRO=?, TIPO_CLIENTE=?, CONDICION_CLIENTE=?, CODIGO_DISTRITO=? WHERE CODIGO_CLIENTE=?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNOMBRE_CLIENTE());
            ps.setString(2, c.getDIRECCION_CLIENTE());
            ps.setString(3, c.getTELEFONO_CLIENTE());
            ps.setString(4, c.getRUC_CLIENTE());
            ps.setDate(5, c.getFECHA_REGISTRO());
            ps.setString(6, c.getTIPO_CLIENTE());
            ps.setString(7, c.getCONDICION_CLIENTE());
            ps.setString(8, c.getCODIGO_DISTRITO());
            ps.setString(9, c.getCODIGO_CLIENTE());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM clientes WHERE CODIGO_CLIENTE = ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigo);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ClienteModel> buscar(String criterio) {
        List<ClienteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE CODIGO_CLIENTE LIKE ? OR NOMBRE_CLIENTE LIKE ? OR RUC_CLIENTE LIKE ?";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            String b = "%" + criterio + "%";
            ps.setString(1, b);
            ps.setString(2, b);
            ps.setString(3, b);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ClienteModel c = new ClienteModel();
                    c.setCODIGO_CLIENTE(rs.getString("CODIGO_CLIENTE"));
                    c.setNOMBRE_CLIENTE(rs.getString("NOMBRE_CLIENTE"));
                    c.setDIRECCION_CLIENTE(rs.getString("DIRECCION_CLIENTE"));
                    c.setTELEFONO_CLIENTE(rs.getString("TELEFONO_CLIENTE"));
                    c.setRUC_CLIENTE(rs.getString("RUC_CLIENTE"));
                    c.setFECHA_REGISTRO(rs.getDate("FECHA_REGISTRO"));
                    c.setTIPO_CLIENTE(rs.getString("TIPO_CLIENTE"));
                    c.setCONDICION_CLIENTE(rs.getString("CONDICION_CLIENTE"));
                    c.setCODIGO_DISTRITO(rs.getString("CODIGO_DISTRITO"));
                    lista.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
