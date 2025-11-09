package com.bodegapp.core.test;

import com.bodegapp.core.database.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection con = ConexionBD.getConexion()) {
            if (con != null && !con.isClosed()) {
                System.out.println("✅ Conexión exitosa a la base de datos!");
            } else {
                System.out.println("⚠️ No se pudo establecer la conexión.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
        }
    }
}