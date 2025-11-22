package com.bodegapp.dashboard.dao;

import com.bodegapp.core.database.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertasDAO {

    public List<AlertaDTO> obtenerAlertas(int idEmpresa) {
        List<AlertaDTO> alertas = new ArrayList<>();

        String sql = 
            "SELECT s.id_solicitud, s.fecha_solicitud, " +
            "       d.codigo_producto, d.cantidad " +
            "FROM solicitud s " +
            "INNER JOIN solicitud_detalle d ON s.id_solicitud = d.id_solicitud " +
            "WHERE s.id_empresa = ? AND s.estado = 'PENDIENTE'";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int idSolicitud = rs.getInt("id_solicitud");
                String fecha = rs.getString("fecha_solicitud");
                String codigoProducto = rs.getString("codigo_producto");
                int cantidad = rs.getInt("cantidad");

                // Construcción del DTO según tu estructura
                String titulo = "Nueva Solicitud #" + idSolicitud;
                String descripcion = 
                    "Producto solicitado: " + codigoProducto + 
                    " | Cantidad: " + cantidad;

                String vendedor = ""; // No existe vendedor en BD
                String itemSolicitado = codigoProducto;

                AlertaDTO alerta = new AlertaDTO(
                    titulo,
                    descripcion,
                    fecha,
                    vendedor,
                    itemSolicitado
                );

                alertas.add(alerta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("TOTAL ALERTAS ENCONTRADAS = " + alertas.size());
        return alertas;
    }
}
