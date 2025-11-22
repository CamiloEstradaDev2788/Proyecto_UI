package com.bodegapp.vendedor.dao;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.inventario.model.ProductoModel;
import com.bodegapp.vendedor.model.VentaRecienteModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {

    // ============================
    // 1. TOTAL VENTAS
    // ============================
    public double obtenerTotalVentas(String codigoVendedor) {
        double total = 0;

        String sql = "SELECT SUM(df.CANTIDAD_VENTA * df.PRECIO_VENTA) AS total " +
                     "FROM factura f " +
                     "JOIN detalle_factura df ON df.NUMERO_FACTURA = f.NUMERO_FACTURA " +
                     "WHERE f.CODIGO_VENDEDOR = ? "; // solo ventas activas

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigoVendedor);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (Exception e) {
            System.out.println("Error obtenerTotalVentas: " + e.getMessage());
        }

        return total;
    }

    // ============================
    // 2. TOTAL PRODUCTOS VENDIDOS
    // ============================
    public int obtenerTotalProductosVendidos(String codigoVendedor) {
        int totalProductos = 0;

        String sql = "SELECT SUM(df.CANTIDAD_VENTA) AS cantidad " +
                     "FROM factura f " +
                     "JOIN detalle_factura df ON df.NUMERO_FACTURA = f.NUMERO_FACTURA " +
                     "WHERE f.CODIGO_VENDEDOR = ? ";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigoVendedor);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalProductos = rs.getInt("cantidad");
            }

        } catch (Exception e) {
            System.out.println("Error obtenerTotalProductosVendidos: " + e.getMessage());
        }

        return totalProductos;
    }

    // ============================
    // 3. TOTAL CLIENTES ATENDIDOS
    // ============================
    public int obtenerTotalClientesAtendidos(String codigoVendedor) {
        int totalClientes = 0;

        String sql = "SELECT COUNT(DISTINCT NUMERO_FACTURA) AS clientes " +
                     "FROM factura " +
                     "WHERE CODIGO_VENDEDOR = ? ";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigoVendedor);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalClientes = rs.getInt("clientes");
            }

        } catch (Exception e) {
            System.out.println("Error obtenerTotalClientesAtendidos: " + e.getMessage());
        }

        return totalClientes;
    }

    // ============================
    // 4. VENTAS RECIENTES (TOP 5)
    // ============================
    public List<VentaRecienteModel> obtenerVentasRecientes(String codigoVendedor) {

        List<VentaRecienteModel> lista = new ArrayList<>();

        String sql = "SELECT p.DESCRIPCION_PRODUCTO, df.CANTIDAD_VENTA, df.PRECIO_VENTA, f.FECHA_FACTURA " +
                     "FROM factura f " +
                     "JOIN detalle_factura df ON df.NUMERO_FACTURA = f.NUMERO_FACTURA " +
                     "JOIN productos p ON p.CODIGO_PRODUCTO = df.CODIGO_PRODUCTO " +
                     "WHERE f.CODIGO_VENDEDOR = ? " +
                     "ORDER BY f.FECHA_FACTURA DESC " +
                     "LIMIT 5";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigoVendedor);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VentaRecienteModel venta = new VentaRecienteModel();
                venta.setProducto(rs.getString("DESCRIPCION_PRODUCTO"));
                venta.setCantidad(rs.getInt("CANTIDAD_VENTA"));
                venta.setPrecio(rs.getDouble("PRECIO_VENTA"));
                venta.setFecha(rs.getDate("FECHA_FACTURA"));

                lista.add(venta);
            }

        } catch (Exception e) {
            System.out.println("Error obtenerVentasRecientes: " + e.getMessage());
        }

        return lista;
    }
    
    
    //Listar Productos Disponibles
    
      // Listar productos disponibles (stock > 0)
    public List<ProductoModel> listarProductosDisponibles() {
        List<ProductoModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE SACO_PRODUCTO > 0";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductoModel p = new ProductoModel();
                p.setCODIGO_PRODUCTO(rs.getString("CODIGO_PRODUCTO"));
                p.setDESCRIPCION_PRODUCTO(rs.getString("DESCRIPCION_PRODUCTO"));
                p.setPRECIO_PRODUCTO(rs.getDouble("PRECIO_PRODUCTO"));
                p.setSACO_PRODUCTO(rs.getInt("SACO_PRODUCTO"));
                p.setMINIMO_STOCK(rs.getInt("MINIMO_STOCK"));
                p.setUNIDAD_PRODUCTO(rs.getString("UNIDAD_PRODUCTO"));
                p.setLINEA_PRODUCTO(rs.getString("LINEA_PRODUCTO"));
                p.setIMPUESTO_PRODUCTO(rs.getString("IMPUESTO_PRODUCTO"));

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error listarProductosDisponibles: " + e.getMessage());
        }

        return lista;
    }
}
