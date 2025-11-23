package com.bodegapp.inventario.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.inventario.dto.ProductoDTO;

public class ProductoDAO {

    private static final String SQL_PRODUCTOS_RECIENTES =
        "SELECT p.codigo_producto, p.descripcion_producto, dc.CANTIDAD_DETALLE, " +
        "       p.precio_producto, p.linea_producto, oc.FECHA_ENTREGA " +
        "FROM productos p " +
        "JOIN detalle_compra dc ON p.codigo_producto = dc.codigo_producto " +
        "JOIN orden_compra oc ON oc.numero_orden = dc.numero_orden " +
        "JOIN proveedores prv ON prv.codigo_proveedor = oc.codigo_proveedor " +
        "WHERE prv.id_empresa = ? " +
        "ORDER BY oc.fecha_entrega DESC " +
        "LIMIT 5";

    public List<ProductoDTO> obtenerProductosRecientes(int idEmpresa) {

        List<ProductoDTO> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(SQL_PRODUCTOS_RECIENTES)) {

            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new ProductoDTO(
                    rs.getString("codigo_producto"),
                    rs.getString("descripcion_producto"),
                    rs.getInt("CANTIDAD_DETALLE"),
                    rs.getDouble("precio_producto"),
                    rs.getString("linea_producto"),
                    rs.getString("FECHA_ENTREGA")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    
    
}

