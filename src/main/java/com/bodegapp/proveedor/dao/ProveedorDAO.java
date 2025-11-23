package com.bodegapp.proveedor.dao;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.proveedor.model.ProveedorModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public List<ProveedorModel> listarProveedores(int ID_EPMRESA) {
        List<ProveedorModel> lista = new ArrayList<>();
        String sql = "SELECT CODIGO_PROVEEDOR, RAZON_SOCIAL, DIRECCION_PROVEEDOR, TELEFONO_PROVEEDOR, REPRESENTANTE_PROVEEDOR, CODIGO_DISTRITO, ID_EMPRESA FROM proveedores";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProveedorModel p = new ProveedorModel(
                        rs.getString("CODIGO_PROVEEDOR"),
                        rs.getString("RAZON_SOCIAL"),
                        rs.getString("DIRECCION_PROVEEDOR"),
                        rs.getString("TELEFONO_PROVEEDOR"),
                        rs.getString("REPRESENTANTE_PROVEEDOR"),
                        rs.getString("CODIGO_DISTRITO"),
                        rs.getInt("ID_EMPRESA")
                );
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
