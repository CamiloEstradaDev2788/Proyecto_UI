package com.bodegapp.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bodegapp.core.database.ConexionBD;

public class DashboardDAO {

    private static final String SQL_TOTAL_VENTAS =
        "SELECT SUM(df.PRECIO_VENTA * df.CANTIDAD_VENTA) AS total_ventas " +
        "FROM empresas e " +
        "JOIN usuarios u ON e.id_empresa = u.id_empresa " +
        "JOIN vendedores v ON v.id_user = u.id_user " +
        "JOIN factura f ON f.codigo_vendedor = v.codigo_vendedor " +
        "JOIN detalle_factura df ON df.NUMERO_FACTURA = f.NUMERO_FACTURA " +
        "WHERE e.id_empresa = ?";

    public double obtenerTotalVentas(int idEmpresa) {

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_TOTAL_VENTAS)) {

            ps.setInt(1, idEmpresa);
            System.out.println("ID EMPRESA RECIBIDO EN DAO = " + idEmpresa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double total = rs.getDouble("total_ventas");
                System.out.println("TOTAL VENTAS DESDE SQL = " + total);
                return total;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
