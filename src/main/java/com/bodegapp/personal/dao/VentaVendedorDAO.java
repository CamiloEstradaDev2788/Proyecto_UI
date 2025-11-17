package com.bodegapp.personal.dao;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.personal.model.VentaVendedorModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaVendedorDAO {
    
    public List<VentaVendedorModel> obtenerVentasPorVendedor(String codigoVendedor) {
        List<VentaVendedorModel> lista = new ArrayList<>();
        
        System.out.println("=== Buscando ventas para código vendedor: " + codigoVendedor + " ===");
        
        String sql = "SELECT f.NUMERO_FACTURA, " +
                     "       f.FECHA_FACTURA, " +
                     "       COALESCE(SUM(df.PRECIO_VENTA * df.CANTIDAD_VENTA), 0) AS TOTAL_VENTA, " +
                     "       COALESCE(SUM(df.CANTIDAD_VENTA), 0) AS CANTIDAD_PRODUCTOS, " +
                     "       COALESCE(c.NOMBRE_CLIENTE, 'Cliente General') AS NOMBRE_CLIENTE " +
                     "FROM factura f " +
                     "LEFT JOIN detalle_factura df ON df.NUMERO_FACTURA = f.NUMERO_FACTURA " +
                     "LEFT JOIN clientes c ON c.CODIGO_CLIENTE = f.CODIGO_CLIENTE " +
                     "WHERE f.CODIGO_VENDEDOR = ? " +
                     "GROUP BY f.NUMERO_FACTURA, f.FECHA_FACTURA, c.NOMBRE_CLIENTE " +
                     "ORDER BY f.FECHA_FACTURA DESC";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, codigoVendedor);
            System.out.println("SQL ejecutado: " + sql.replace("?", "'" + codigoVendedor + "'"));
            
            try (ResultSet rs = ps.executeQuery()) {
                int contador = 0;
                while (rs.next()) {
                    contador++;
                    VentaVendedorModel v = new VentaVendedorModel();
                    
                    String numFactura = rs.getString("NUMERO_FACTURA");
                    if (numFactura != null) {
                        v.setNUMERO_FACTURA(numFactura);
                        v.setFECHA_FACTURA(rs.getDate("FECHA_FACTURA"));
                        
                        Double total = rs.getDouble("TOTAL_VENTA");
                        v.setTOTAL_VENTA(rs.wasNull() ? 0.0 : total);
                        
                        Integer cantidad = rs.getInt("CANTIDAD_PRODUCTOS");
                        v.setCANTIDAD_PRODUCTOS(rs.wasNull() ? 0 : cantidad);
                        
                        String cliente = rs.getString("NOMBRE_CLIENTE");
                        v.setNOMBRE_CLIENTE(cliente != null ? cliente : "Cliente General");
                        
                        System.out.println("Venta encontrada #" + contador + ": " + numFactura + " - Total: " + v.getTOTAL_VENTA() + " - Cantidad: " + v.getCANTIDAD_PRODUCTOS());
                        lista.add(v);
                    }
                }
                System.out.println("Total de ventas encontradas: " + contador);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al obtener ventas del vendedor: " + e.getMessage());
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public double obtenerTotalVentasVendedor(String codigoVendedor) {
        double total = 0;
        
        String sql = "SELECT SUM(df.PRECIO_VENTA * df.CANTIDAD_VENTA) AS TOTAL " +
                     "FROM factura f " +
                     "JOIN detalle_factura df ON df.NUMERO_FACTURA = f.NUMERO_FACTURA " +
                     "WHERE f.CODIGO_VENDEDOR = ?";
        
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, codigoVendedor);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = rs.getDouble("TOTAL");
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error al obtener total de ventas: " + e.getMessage());
            e.printStackTrace();
        }
        
        return total;
    }
}

