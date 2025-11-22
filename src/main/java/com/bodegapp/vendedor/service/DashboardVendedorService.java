package com.bodegapp.vendedor.service;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.inventario.dao.InventarioDAO;
import com.bodegapp.inventario.model.InventarioModel;
import com.bodegapp.inventario.model.ProductoModel;
import com.bodegapp.usuarios.model.UsuarioModel;
import com.bodegapp.vendedor.dao.VendedorDAO;
import com.bodegapp.vendedor.model.VentaRecienteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class DashboardVendedorService {

    private VendedorDAO dao = new VendedorDAO();

    public double obtenerTotalVentas(String codigoVendedor) {
        return dao.obtenerTotalVentas(codigoVendedor);
    }

    public int obtenerTotalProductosVendidos(String codigoVendedor) {
        return dao.obtenerTotalProductosVendidos(codigoVendedor);
    }

    public int obtenerTotalClientesAtendidos(String codigoVendedor) {
        return dao.obtenerTotalClientesAtendidos(codigoVendedor);
    }

    public List<VentaRecienteModel> obtenerVentasRecientes(String codigoVendedor) {
        return dao.obtenerVentasRecientes(codigoVendedor);
    }
    
    public List<ProductoModel> listarProductos() {
        return dao.listarProductosDisponibles();
    }
    

public boolean solicitarProducto(int idEmpresa, String codigoProducto, int cantidad) {

    Connection con = null;

    try {
        con = ConexionBD.getConexion();
        con.setAutoCommit(false);

        // 1. Obtener precio del producto
        PreparedStatement psPrecio = con.prepareStatement(
            "SELECT PRECIO_PRODUCTO FROM productos WHERE CODIGO_PRODUCTO = ?"
        );
        psPrecio.setString(1, codigoProducto);
        ResultSet rs = psPrecio.executeQuery();

        if (!rs.next()) {
            throw new Exception("Producto no encontrado: " + codigoProducto);
        }

        double precio = rs.getDouble("PRECIO_PRODUCTO");

        // 2. Crear solicitud (cabecera)
        PreparedStatement psSolicitud = con.prepareStatement(
            "INSERT INTO solicitud (id_empresa) VALUES (?)",
            PreparedStatement.RETURN_GENERATED_KEYS
        );
        psSolicitud.setInt(1, idEmpresa);
        psSolicitud.executeUpdate();

        ResultSet rsClave = psSolicitud.getGeneratedKeys();
        if (!rsClave.next()) throw new Exception("No se generó ID de solicitud");
        int idSolicitud = rsClave.getInt(1);

        // 3. Insertar detalle de solicitud
        PreparedStatement psDetalle = con.prepareStatement(
            "INSERT INTO solicitud_detalle (id_solicitud, codigo_producto, cantidad, precio) " +
            "VALUES (?, ?, ?, ?)"
        );
        psDetalle.setInt(1, idSolicitud);
        psDetalle.setString(2, codigoProducto);
        psDetalle.setInt(3, cantidad);
        psDetalle.setDouble(4, precio);
        psDetalle.executeUpdate();

        con.commit();
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        try { if (con != null) con.rollback(); } catch (Exception ex) {}
        return false;

    } finally {
        try { if (con != null) con.close(); } catch (Exception e) {}
    }
}


    // Listar inventario
    // Listar inventario
// Listar inventario del vendedor
public List<InventarioModel> listarInventario(int idEmpresa) {

    InventarioDAO dao = new InventarioDAO();
    return dao.listar(idEmpresa);
}



    
}
