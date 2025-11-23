package com.bodegapp.vendedor.dao;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.vendedor.model.NuevaVentaDTO;
import com.bodegapp.vendedor.model.NuevoDetalleVentaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NuevaVentaDAO {
    
    
    public boolean existeCliente(String codigoCliente){
        
        String SQL = "SELECT COUNT(*) FROM clientes WHERE CODIGO_CLIENTE = ?";
        
        try(Connection con = ConexionBD.getConexion();
                PreparedStatement ps = con.prepareStatement(SQL)){
            
                    ps.setString(1, codigoCliente);
                    ResultSet rs = ps.executeQuery();
                    
                    if(rs.next()){
                        return rs.getInt(1)>0;
                    }
        }catch(SQLException e){
            e.printStackTrace();
        }
    return false;
    }
    
    //Registrar venta Completa con detalle de venta
    
    public String registrarVenta(NuevaVentaDTO venta, List<NuevoDetalleVentaDTO> detalles) throws SQLException {

    Connection con = null;
    PreparedStatement psVenta = null;
    PreparedStatement psDetalle = null;


    String SQLVenta = "INSERT INTO factura "
            + "(NUMERO_FACTURA, CODIGO_VENDEDOR, CODIGO_CLIENTE, FECHA_FACTURA, FECHA_CANCELACION, ESTADO_FACTURA, PORCENTAJE_IGV) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    String SQLDetalle = "INSERT INTO detalle_factura "
            + "(NUMERO_FACTURA, CODIGO_PRODUCTO, CANTIDAD_VENTA, PRECIO_VENTA) "
            + "VALUES (?, ?, ?, ?)";

    try {

        con = ConexionBD.getConexion();
        con.setAutoCommit(false); // INICIA TRANSACCIÓN

        String idVentaGenerado = generarCodigoFactura(con);
        // INSERTAR VENTA
        psVenta = con.prepareStatement(SQLVenta);
        psVenta.setString(1, idVentaGenerado);
        psVenta.setString(2, venta.getCODIGO_VENDEDOR());
        psVenta.setString(3, venta.getCODIGO_CLIENTE());
        psVenta.setDate(4, venta.getFECHA_FACTURA());
        psVenta.setDate(5, venta.getFECHA_CANCELACION());
        psVenta.setString(6, venta.getESTADO_FACTURA());
        psVenta.setDouble(7, venta.getPROCENTAJE_IGV()); // tu campo es String

        psVenta.executeUpdate();

        // INSERTAR DETALLES
        psDetalle = con.prepareStatement(SQLDetalle);

        for (NuevoDetalleVentaDTO d : detalles) {
            psDetalle.setString(1, idVentaGenerado);
            psDetalle.setString(2, d.getCODIGO_PRODUCTO());
            psDetalle.setInt(3, d.getCANTIDAD_VENTA());
            psDetalle.setDouble(4, d.getPRECIO_VENTA());
            psDetalle.addBatch();
        }

        psDetalle.executeBatch();

        con.commit(); // CONFIRMAR TRANSACCIÓN

        return idVentaGenerado;

    } catch (SQLException e) {

        if (con != null) {
            con.rollback(); // DESHACER TODO
        }
        throw e;

    } finally {

        if (psVenta != null) psVenta.close();
        if (psDetalle != null) psDetalle.close();
        if (con != null) con.close();
    }
}

    public String generarCodigoFactura(Connection conn) {
    String nuevoCodigo = "FA001"; // Valor por defecto si no hay facturas
    String sql = "SELECT NUMERO_FACTURA FROM FACTURA ORDER BY NUMERO_FACTURA DESC LIMIT 1;";

    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            String ultimoCodigo = rs.getString("NUMERO_FACTURA"); // Ej: FA023
            // Extraer la parte numérica
            String numeroStr = ultimoCodigo.substring(2); // "023"
            int numero = Integer.parseInt(numeroStr);
            numero++; // Incrementar
            // Formatear con ceros a la izquierda
            String numeroFormateado = String.format("%03d", numero);
            nuevoCodigo = "FA" + numeroFormateado; // Ej: FA024
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return nuevoCodigo;
}

}
