package com.bodegapp.inventario.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bodegapp.inventario.model.InventarioModel;
import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.proveedor.model.ProveedorModel;

public class InventarioDAO {

    // LISTAR productos por empresa
    public List<InventarioModel> listar(int idEmpresa) {
        List<InventarioModel> lista = new ArrayList<>();
        String sql = "SELECT p.*, " +
             " COALESCE(GROUP_CONCAT(DISTINCT pr.RAZON_SOCIAL SEPARATOR ', '), 'Sin proveedor') AS NOMBRE_PROVEEDOR " +
             "FROM productos p " +
             "JOIN abastecimiento a ON a.CODIGO_PRODUCTO = p.CODIGO_PRODUCTO " +
             "JOIN proveedores pr ON pr.CODIGO_PROVEEDOR = a.CODIGO_PROVEEDOR " +
             "JOIN empresas em ON em.ID_EMPRESA = pr.ID_EMPRESA " +
             "WHERE em.ID_EMPRESA = ? AND p.estado = 1 " +
             "GROUP BY p.CODIGO_PRODUCTO, p.DESCRIPCION_PRODUCTO, p.PRECIO_PRODUCTO, " +
             "         p.SACO_PRODUCTO, p.MINIMO_STOCK, p.UNIDAD_PRODUCTO, " +
             "         p.LINEA_PRODUCTO, p.IMPUESTO_PRODUCTO";


        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();

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
            e.printStackTrace();
        }

        return lista;
    }

    // BUSCAR productos por criterio
    public List<InventarioModel> buscar(String criterio) {
        List<InventarioModel> lista = new ArrayList<>();
        String sql = "SELECT p.*, "
           + " COALESCE(GROUP_CONCAT(DISTINCT pr.RAZON_SOCIAL SEPARATOR ', '), 'Sin proveedor') AS NOMBRE_PROVEEDOR "
           + "FROM productos p "
           + "JOIN abastecimiento a ON a.CODIGO_PRODUCTO = p.CODIGO_PRODUCTO "
           + "JOIN proveedores pr ON pr.CODIGO_PROVEEDOR = a.CODIGO_PROVEEDOR "
           + "JOIN empresas em ON em.ID_EMPRESA = pr.ID_EMPRESA "
           + "WHERE em.ID_EMPRESA = ? "
           + "  AND p.estado = 1 "
           + "GROUP BY p.CODIGO_PRODUCTO, p.DESCRIPCION_PRODUCTO, p.PRECIO_PRODUCTO, "
           + "         p.SACO_PRODUCTO, p.MINIMO_STOCK, p.UNIDAD_PRODUCTO, "
           + "         p.LINEA_PRODUCTO, p.IMPUESTO_PRODUCTO";

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
            e.printStackTrace();
        }

        return lista;
    }

    // REGISTRAR nuevo producto con abastecimiento y orden de compra
    public boolean registrar(InventarioModel p, ProveedorModel prv) {
        String sql = "INSERT INTO productos (CODIGO_PRODUCTO, DESCRIPCION_PRODUCTO, PRECIO_PRODUCTO, SACO_PRODUCTO, MINIMO_STOCK, UNIDAD_PRODUCTO, LINEA_PRODUCTO, IMPUESTO_PRODUCTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String abastecimientoSQL = "INSERT INTO abastecimiento (CODIGO_PRODUCTO, CODIGO_PROVEEDOR, PRECIO_ABASTECIMIENTO) VALUES (?, ?, ?)";
        String ordenCompraSQL = "INSERT INTO orden_compra (NUMERO_ORDEN, CODIGO_PROVEEDOR, FECHA_COMPRA, FECHA_ENTREGA, ESTADO_ORDEN) VALUES (?, ?, ?, ?, ?)";
        String detalleComprSQL = "INSERT INTO detalle_compra (NUMERO_ORDEN, CODIGO_PRODUCTO, CANTIDAD_DETALLE) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             PreparedStatement pa = con.prepareStatement(abastecimientoSQL);
             PreparedStatement poc = con.prepareStatement(ordenCompraSQL);
             PreparedStatement pdc = con.prepareStatement(detalleComprSQL)) {

            // Variables
            String numero_orden = generarCodigoNuevaOrdenCompra(con);
            Date fechaCompra = new java.sql.Date(System.currentTimeMillis());
            long sieteDiasMs = 7L * 24 * 60 * 60 * 1000;
            Date fechaEntrega = new Date(System.currentTimeMillis() + sieteDiasMs);

            ps.setString(1, p.getCODIGO_PRODUCTO());
            ps.setString(2, p.getDESCRIPCION_PRODUCTO());
            ps.setDouble(3, p.getPRECIO_PRODUCTO());
            ps.setInt(4, p.getSACO_PRODUCTO());
            ps.setInt(5, p.getMINIMO_STOCK());
            ps.setString(6, p.getUNIDAD_PRODUCTO());
            ps.setString(7, p.getLINEA_PRODUCTO());
            ps.setString(8, p.getIMPUESTO_PRODUCTO());

            pa.setString(1, p.getCODIGO_PRODUCTO());
            pa.setString(2, prv.getCODIGO_PROVEEDOR());
            pa.setDouble(3, p.getPRECIO_PRODUCTO());

            poc.setString(1, numero_orden);
            poc.setString(2, prv.getCODIGO_PROVEEDOR());
            poc.setDate(3, fechaCompra);
            poc.setDate(4, fechaEntrega);
            poc.setString(5, "Pendiente");

            pdc.setString(1, numero_orden);
            pdc.setString(2, p.getCODIGO_PRODUCTO());
            pdc.setInt(3, p.getMINIMO_STOCK());

            ps.executeUpdate();
            pa.executeUpdate();
            poc.executeUpdate();
            pdc.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // OBTENER producto por código
    public InventarioModel obtenerProducto(String codigoProducto) {
        InventarioModel p = null;
        String sql = "SELECT * FROM productos WHERE CODIGO_PRODUCTO = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigoProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new InventarioModel();
                    p.setCODIGO_PRODUCTO(rs.getString("CODIGO_PRODUCTO"));
                    p.setDESCRIPCION_PRODUCTO(rs.getString("DESCRIPCION_PRODUCTO"));
                    p.setPRECIO_PRODUCTO(rs.getDouble("PRECIO_PRODUCTO"));
                    p.setSACO_PRODUCTO(rs.getInt("SACO_PRODUCTO"));
                    p.setMINIMO_STOCK(rs.getInt("MINIMO_STOCK"));
                    p.setUNIDAD_PRODUCTO(rs.getString("UNIDAD_PRODUCTO"));
                    p.setLINEA_PRODUCTO(rs.getString("LINEA_PRODUCTO"));
                    p.setIMPUESTO_PRODUCTO(rs.getString("IMPUESTO_PRODUCTO"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    // ACTUALIZAR producto
    public boolean actualizarProducto(InventarioModel p) {
        String sql = "UPDATE productos SET DESCRIPCION_PRODUCTO = ?, PRECIO_PRODUCTO = ?, SACO_PRODUCTO = ?, MINIMO_STOCK = ?, UNIDAD_PRODUCTO = ?, LINEA_PRODUCTO = ?, IMPUESTO_PRODUCTO = ? WHERE CODIGO_PRODUCTO = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getDESCRIPCION_PRODUCTO());
            ps.setDouble(2, p.getPRECIO_PRODUCTO());
            ps.setInt(3, p.getSACO_PRODUCTO());
            ps.setInt(4, p.getMINIMO_STOCK());
            ps.setString(5, p.getUNIDAD_PRODUCTO());
            ps.setString(6, p.getLINEA_PRODUCTO());
            ps.setString(7, p.getIMPUESTO_PRODUCTO());
            ps.setString(8, p.getCODIGO_PRODUCTO());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR producto
public boolean eliminarProducto(String codigoProducto) {
    String sql = "UPDATE productos SET estado = 0 WHERE CODIGO_PRODUCTO = ?";

    try (Connection con = ConexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, codigoProducto);
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    // GENERAR nuevo código de orden de compra
    public String generarCodigoNuevaOrdenCompra(Connection conn) {
        String nuevoCodigo = "OC001"; // Valor por defecto
        String sql = "SELECT NUMERO_ORDEN FROM orden_compra ORDER BY NUMERO_ORDEN DESC LIMIT 1";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String ultimoCodigo = rs.getString("NUMERO_ORDEN");
                int numero = Integer.parseInt(ultimoCodigo.substring(2)) + 1;
                nuevoCodigo = "OC" + String.format("%03d", numero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nuevoCodigo;
    }
}
