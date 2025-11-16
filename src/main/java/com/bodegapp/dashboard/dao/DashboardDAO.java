package com.bodegapp.dashboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.inventario.dao.ProductoDAO;
import com.bodegapp.inventario.dto.ProductoDTO;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO {

    /*----------------------Insights-----------------------------------------*/
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
    
    public double obtenerGastosTotales(int idEmpresa, String fechaInicio, String fechaFin) {

    String SQL_GASTOS_TOTALES =
        "SELECT " +
        "   (SELECT SUM(u.SALARIO) " +
        "    FROM usuarios u " +
        "    WHERE u.id_empresa = ?) " +
        "   + " +
        "   (SELECT SUM(dc.CANTIDAD_DETALLE * p.PRECIO_PRODUCTO) " +
        "    FROM proveedores pr " +
        "    JOIN orden_compra oc ON oc.codigo_proveedor = pr.codigo_proveedor " +
        "    JOIN detalle_compra dc ON dc.numero_orden = oc.numero_orden " +
        "    JOIN productos p ON p.codigo_producto = dc.codigo_producto " +
        "    WHERE pr.id_empresa = ? " +
        "      AND oc.FECHA_COMPRA BETWEEN ? AND ?) " +
        "AS gastos_totales";

    try (Connection con = ConexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(SQL_GASTOS_TOTALES)) {

        ps.setInt(1, idEmpresa);
        ps.setInt(2, idEmpresa);
        ps.setString(3, fechaInicio);
        ps.setString(4, fechaFin);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getDouble("gastos_totales");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}

    public double obtenerGanancias(int idEmpresa, String fechaInicio, String fechaFin) {
    double ventas = obtenerTotalVentas(idEmpresa);
    double gastos = obtenerGastosTotales(idEmpresa, fechaInicio, fechaFin); 
    return ventas - gastos;
}
    
    /*----------------------Insights-----------------------------------------*/
    
    /*----------------------Tabla de productos-----------------------------------------*/
    
    public List<ProductoDTO> obtenerProductosRecientes(int idEmpresa) {

    List<ProductoDTO> lista = new ArrayList<>();

    String SQL =
        "SELECT " +
        "p.codigo_producto AS codigoProducto, " +
        "p.descripcion_producto AS descripcion, " +
        "dc.cantidad_detalle AS cantidad, " +
        "p.precio_producto AS precio, " +
        "p.linea_producto AS linea, " +
        "oc.fecha_entrega AS fechaEntrega " +
        "FROM productos p " +
        "JOIN detalle_compra dc ON p.codigo_producto = dc.codigo_producto " +
        "JOIN orden_compra oc ON oc.numero_orden = dc.numero_orden " +
        "JOIN proveedores prv ON prv.codigo_proveedor = oc.codigo_proveedor " +
        "WHERE prv.id_empresa = ? " +
        "ORDER BY oc.fecha_entrega DESC " +
        "LIMIT 10";

    try (Connection con = ConexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(SQL)) {

        ps.setInt(1, idEmpresa);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductoDTO dto = new ProductoDTO(
                    rs.getString("codigoProducto"),
                    rs.getString("descripcion"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio"),
                    rs.getString("linea"),
                    rs.getString("fechaEntrega")
                );
                lista.add(dto);
            }
        }

    } catch (Exception e) {
        System.out.println("❌ Error en obtenerProductosRecientes(): " + e.getMessage());
        e.printStackTrace();
    }

    return lista;
}

    
}
