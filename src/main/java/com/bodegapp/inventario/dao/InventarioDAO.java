package com.bodegapp.inventario.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bodegapp.inventario.model.InventarioModel;
import com.bodegapp.core.database.ConexionBD;

public class InventarioDAO {

    public List<InventarioModel> listar() {
        List<InventarioModel> lista = new ArrayList<>();

        // Consulta con JOIN a través de la tabla abastecimiento
        String sql = "SELECT p.*, " +
                     "       COALESCE(GROUP_CONCAT(DISTINCT pr.RAZON_SOCIAL SEPARATOR ', '), 'Sin proveedor') AS NOMBRE_PROVEEDOR " +
                     "FROM productos p " +
                     "LEFT JOIN abastecimiento a ON a.CODIGO_PRODUCTO = p.CODIGO_PRODUCTO " +
                     "LEFT JOIN proveedores pr ON pr.CODIGO_PROVEEDOR = a.CODIGO_PROVEEDOR " +
                     "GROUP BY p.CODIGO_PRODUCTO, p.DESCRIPCION_PRODUCTO, p.PRECIO_PRODUCTO, " +
                     "         p.SACO_PRODUCTO, p.MINIMO_STOCK, p.UNIDAD_PRODUCTO, " +
                     "         p.LINEA_PRODUCTO, p.IMPUESTO_PRODUCTO";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                InventarioModel p = new InventarioModel();

                p.setCODIGO_PRODUCTO(rs.getString("CODIGO_PRODUCTO"));
                p.setDESCRIPCION_PRODUCTO(rs.getString("DESCRIPCION_PRODUCTO"));
                p.setPRECIO_PRODUCTO(rs.getDouble("PRECIO_PRODUCTO"));
                p.setSACO_PRODUCTO(rs.getInt("SACO_PRODUCTO"));
                p.setMINIMO_STOCK(rs.getInt("MINIMO_STOCK"));
                p.setUNIDAD_PRODUCTO(rs.getString("UNIDAD_PRODUCTO"));
                p.setLINEA_PRODUCTO(rs.getString("LINEA_PRODUCTO"));
                p.setIMPUESTO_PRODUCTO(rs.getString("IMPUESTO_PRODUCTO"));
                p.setNOMBRE_PROVEEDOR(rs.getString("NOMBRE_PROVEEDOR"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public List<InventarioModel> buscar(String criterio) {
        List<InventarioModel> lista = new ArrayList<>();

        // Consulta de búsqueda con JOIN para incluir proveedores
        String sql = "SELECT p.*, " +
                     "       COALESCE(GROUP_CONCAT(DISTINCT pr.RAZON_SOCIAL SEPARATOR ', '), 'Sin proveedor') AS NOMBRE_PROVEEDOR " +
                     "FROM productos p " +
                     "LEFT JOIN abastecimiento a ON a.CODIGO_PRODUCTO = p.CODIGO_PRODUCTO " +
                     "LEFT JOIN proveedores pr ON pr.CODIGO_PROVEEDOR = a.CODIGO_PROVEEDOR " +
                     "WHERE p.CODIGO_PRODUCTO LIKE ? " +
                     "   OR p.DESCRIPCION_PRODUCTO LIKE ? " +
                     "   OR p.LINEA_PRODUCTO LIKE ? " +
                     "   OR pr.RAZON_SOCIAL LIKE ? " +
                     "GROUP BY p.CODIGO_PRODUCTO, p.DESCRIPCION_PRODUCTO, p.PRECIO_PRODUCTO, " +
                     "         p.SACO_PRODUCTO, p.MINIMO_STOCK, p.UNIDAD_PRODUCTO, " +
                     "         p.LINEA_PRODUCTO, p.IMPUESTO_PRODUCTO";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            String busqueda = "%" + criterio + "%";
            ps.setString(1, busqueda);
            ps.setString(2, busqueda);
            ps.setString(3, busqueda);
            ps.setString(4, busqueda);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    InventarioModel p = new InventarioModel();

                    p.setCODIGO_PRODUCTO(rs.getString("CODIGO_PRODUCTO"));
                    p.setDESCRIPCION_PRODUCTO(rs.getString("DESCRIPCION_PRODUCTO"));
                    p.setPRECIO_PRODUCTO(rs.getDouble("PRECIO_PRODUCTO"));
                    p.setSACO_PRODUCTO(rs.getInt("SACO_PRODUCTO"));
                    p.setMINIMO_STOCK(rs.getInt("MINIMO_STOCK"));
                    p.setUNIDAD_PRODUCTO(rs.getString("UNIDAD_PRODUCTO"));
                    p.setLINEA_PRODUCTO(rs.getString("LINEA_PRODUCTO"));
                    p.setIMPUESTO_PRODUCTO(rs.getString("IMPUESTO_PRODUCTO"));
                    p.setNOMBRE_PROVEEDOR(rs.getString("NOMBRE_PROVEEDOR"));

                    lista.add(p);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al buscar productos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public boolean registrar(InventarioModel p) {
        String sql = "INSERT INTO productos (CODIGO_PRODUCTO, DESCRIPCION_PRODUCTO, PRECIO_PRODUCTO, SACO_PRODUCTO, MINIMO_STOCK, UNIDAD_PRODUCTO, LINEA_PRODUCTO, IMPUESTO_PRODUCTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getCODIGO_PRODUCTO());
            ps.setString(2, p.getDESCRIPCION_PRODUCTO());
            ps.setDouble(3, p.getPRECIO_PRODUCTO());
            ps.setInt(4, p.getSACO_PRODUCTO());
            ps.setInt(5, p.getMINIMO_STOCK());
            ps.setString(6, p.getUNIDAD_PRODUCTO());
            ps.setString(7, p.getLINEA_PRODUCTO());
            ps.setString(8, p.getIMPUESTO_PRODUCTO());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
