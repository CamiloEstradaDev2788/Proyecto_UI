package com.bodegapp.auditoria.dao;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.auditoria.model.AuditoriaModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditoriaDAO {

    // LISTAR POR RANGO DE FECHAS
    public List<AuditoriaModel> listar(String fechaInicio, String fechaFin) {
        List<AuditoriaModel> lista = new ArrayList<>();

        String sql = "SELECT id_auditoria, nombre_tabla, operacion, pk_registro, usuario_app, fecha_hora, "
                   + "valor_anterior, valor_nuevo "
                   + "FROM auditoria "
                   + "WHERE fecha_hora BETWEEN ? AND ? "
                   + "ORDER BY fecha_hora DESC";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, fechaInicio + " 00:00:00");
            ps.setString(2, fechaFin + " 23:59:59");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AuditoriaModel a = new AuditoriaModel();

                a.setIdAuditoria(rs.getInt("id_auditoria"));
                a.setNombreTabla(rs.getString("nombre_tabla"));
                a.setOperacion(rs.getString("operacion"));
                a.setPkRegistro(rs.getString("pk_registro"));
                a.setUsuarioApp(rs.getString("usuario_app"));
                a.setFechaHora(rs.getTimestamp("fecha_hora"));
                a.setValorAnterior(rs.getString("valor_anterior"));
                a.setValorNuevo(rs.getString("valor_nuevo"));

                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // INSERTAR REGISTRO EN AUDITORIA
    public void registrar(AuditoriaModel a) {

        String sql = "INSERT INTO auditoria(nombre_tabla, operacion, pk_registro, usuario_app, fecha_hora, valor_anterior, valor_nuevo) "
                   + "VALUES (?, ?, ?, ?, NOW(), ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, a.getNombreTabla());
            ps.setString(2, a.getOperacion());
            ps.setString(3, a.getPkRegistro());
            ps.setString(4, a.getUsuarioApp());
            ps.setString(5, a.getValorAnterior());
            ps.setString(6, a.getValorNuevo());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
