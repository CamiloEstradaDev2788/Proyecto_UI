package com.bodegapp.personal.dao;

import com.bodegapp.core.database.ConexionBD;
import com.bodegapp.personal.model.PersonalModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonalDAO {

    // LISTAR PERSONAL
    public List<PersonalModel> listar(int idEmpresa) {
        List<PersonalModel> lista = new ArrayList<>();
        String sql = "SELECT u.ID_USER, u.ID_EMPRESA, u.ID_ROL, u.NOMBRE1, u.NOMBRE2, u.APELLIDO1, u.APELLIDO2, " +
             "u.CEDULA, u.CORREO, u.SALARIO, u.TIPO_CONTRATO, u.ESTADO, r.NOMBRE_ROL, " +
             "CASE WHEN v.CODIGO_VENDEDOR IS NOT NULL THEN 1 ELSE 0 END AS ES_VENDEDOR, v.CODIGO_VENDEDOR " +
             "FROM usuarios u " +
             "LEFT JOIN roles r ON r.ID_ROL = u.ID_ROL " +
             "LEFT JOIN vendedores v ON v.ID_USER = u.ID_USER " +
             "WHERE u.ID_EMPRESA = ? " +
             "AND u.ESTADO = 1 " +   // 🔥 SOLO ACTIVOS
             "ORDER BY u.NOMBRE1, u.APELLIDO1";


        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PersonalModel p = new PersonalModel();
                    p.setID_USER(rs.getInt("ID_USER"));
                    p.setID_EMPRESA(rs.getInt("ID_EMPRESA"));
                    p.setID_ROL(rs.getInt("ID_ROL"));
                    p.setNOMBRE1(rs.getString("NOMBRE1"));
                    p.setNOMBRE2(rs.getString("NOMBRE2"));
                    p.setAPELLIDO1(rs.getString("APELLIDO1"));
                    p.setAPELLIDO2(rs.getString("APELLIDO2"));
                    p.setCEDULA(rs.getString("CEDULA"));
                    p.setCORREO(rs.getString("CORREO"));
                    p.setSALARIO(rs.getDouble("SALARIO"));
                    p.setTIPO_CONTRATO(rs.getString("TIPO_CONTRATO"));
                    p.setESTADO(rs.getBoolean("ESTADO"));
                    p.setES_VENDEDOR(rs.getInt("ES_VENDEDOR") == 1);
                    p.setCODIGO_VENDEDOR(rs.getString("CODIGO_VENDEDOR"));
                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // OBTENER PERSONAL POR ID
    public PersonalModel obtenerPorId(int idUser) {
        PersonalModel p = null;
        String sql = "SELECT u.ID_USER, u.ID_EMPRESA, u.ID_ROL, u.NOMBRE1, u.NOMBRE2, u.APELLIDO1, u.APELLIDO2, " +
                     "u.CEDULA, u.CORREO, u.SALARIO, u.TIPO_CONTRATO, u.ESTADO, u.FECHA_INGRESO, " +
                     "v.CODIGO_VENDEDOR, v.CODIGO_DISTRITO, v.FECHA_INGRESO AS FECHA_INGRESO_VENDEDOR, v.TIPO_VENDEDOR " +
                     "FROM usuarios u " +
                     "LEFT JOIN vendedores v ON v.ID_USER = u.ID_USER " +
                     "WHERE u.ID_USER = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUser);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new PersonalModel();
                    p.setID_USER(rs.getInt("ID_USER"));
                    p.setID_EMPRESA(rs.getInt("ID_EMPRESA"));
                    p.setID_ROL(rs.getInt("ID_ROL"));
                    p.setNOMBRE1(rs.getString("NOMBRE1"));
                    p.setNOMBRE2(rs.getString("NOMBRE2"));
                    p.setAPELLIDO1(rs.getString("APELLIDO1"));
                    p.setAPELLIDO2(rs.getString("APELLIDO2"));
                    p.setCEDULA(rs.getString("CEDULA"));
                    p.setCORREO(rs.getString("CORREO"));
                    p.setSALARIO(rs.getDouble("SALARIO"));
                    p.setTIPO_CONTRATO(rs.getString("TIPO_CONTRATO"));
                    p.setESTADO(rs.getBoolean("ESTADO"));

                    Date fechaIngreso = rs.getDate("FECHA_INGRESO");
                    if (fechaIngreso != null) p.setFECHA_INGRESO(new java.util.Date(fechaIngreso.getTime()));

                    p.setCODIGO_VENDEDOR(rs.getString("CODIGO_VENDEDOR"));
                    p.setCODIGO_DISTRITO(rs.getString("CODIGO_DISTRITO"));

                    Date fechaVen = rs.getDate("FECHA_INGRESO_VENDEDOR");
                    if (fechaVen != null) p.setFECHA_INGRESO_VENDEDOR(new java.util.Date(fechaVen.getTime()));

                    p.setTIPO_VENDEDOR(rs.getString("TIPO_VENDEDOR"));
                    p.setES_VENDEDOR(rs.getString("CODIGO_VENDEDOR") != null);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    // GENERAR NUEVO CODIGO VENDEDOR
    public String generarNuevoCodigoVendedor() {
        String sql = "SELECT CODIGO_VENDEDOR FROM vendedores ORDER BY CAST(SUBSTRING(CODIGO_VENDEDOR,2) AS UNSIGNED) DESC LIMIT 1";
        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String ultimo = rs.getString("CODIGO_VENDEDOR");
                if (ultimo != null && ultimo.length() >= 2) {
                    int num = Integer.parseInt(ultimo.substring(1)) + 1;
                    return (num < 10) ? "V0" + num : "V" + num;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "V01";
    }

    // INSERTAR USUARIO (+ OPCIONAL VENDEDOR)
    // INSERTAR USUARIO (+ OPCIONAL VENDEDOR) con debug
    // INSERTAR USUARIO (+ VENDEDOR según rol)
    public boolean insertar(PersonalModel p) {
    String insertUser = "INSERT INTO usuarios (ID_EMPRESA, ID_ROL, NOMBRE1, NOMBRE2, APELLIDO1, APELLIDO2, CEDULA, CORREO, CONTRASENA_HASH, FECHA_INGRESO, SALARIO, TIPO_CONTRATO, ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String insertVendedor = "INSERT INTO vendedores (CODIGO_VENDEDOR, ID_USER, CODIGO_DISTRITO, FECHA_INGRESO, TIPO_VENDEDOR) VALUES (?, ?, ?, ?, ?)";

    try (Connection con = ConexionBD.getConexion()) {
        con.setAutoCommit(false);

        int newId;
        try (PreparedStatement ps = con.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, p.getID_EMPRESA());
            ps.setInt(2, p.getID_ROL());
            ps.setString(3, p.getNOMBRE1());
            ps.setString(4, p.getNOMBRE2());
            ps.setString(5, p.getAPELLIDO1());
            ps.setString(6, p.getAPELLIDO2());
            ps.setString(7, p.getCEDULA());
            ps.setString(8, p.getCORREO());
            ps.setString(9, p.getCONTRASENA_HASH());
            ps.setDate(10, new java.sql.Date(System.currentTimeMillis()));
            ps.setDouble(11, p.getSALARIO());
            ps.setString(12, p.getTIPO_CONTRATO());
            ps.setBoolean(13, p.isESTADO());

            int affected = ps.executeUpdate();
            if (affected == 0) throw new SQLException("No se insertó el usuario.");

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) newId = rs.getInt(1);
                else throw new SQLException("No se obtuvo ID_USER.");
            }
        }

        if (p.isES_VENDEDOR()) {  // ahora depende del hidden input del JSP
            String codigo = (p.getCODIGO_VENDEDOR() == null || p.getCODIGO_VENDEDOR().isEmpty())
                            ? generarNuevoCodigoVendedor()
                            : p.getCODIGO_VENDEDOR();

            String distrito = (p.getCODIGO_DISTRITO() != null) ? p.getCODIGO_DISTRITO() : "";
            String tipo = (p.getTIPO_VENDEDOR() != null) ? p.getTIPO_VENDEDOR() : "";

            try (PreparedStatement pv = con.prepareStatement(insertVendedor)) {
                pv.setString(1, codigo);
                pv.setInt(2, newId);
                pv.setString(3, distrito);
                pv.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                pv.setString(5, tipo);
                pv.executeUpdate(); // inserta en la BD
            }
        }

        con.commit();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
   // ACTUALIZAR USUARIO (+ VENDEDOR)
    public boolean actualizar(PersonalModel p) {
        String updateUser = "UPDATE usuarios SET ID_ROL = ?, NOMBRE1 = ?, NOMBRE2 = ?, APELLIDO1 = ?, APELLIDO2 = ?, CEDULA = ?, CORREO = ?, SALARIO = ?, TIPO_CONTRATO = ?, ESTADO = ? WHERE ID_USER = ?";
        String insertVendedor = "INSERT INTO vendedores (CODIGO_VENDEDOR, ID_USER, CODIGO_DISTRITO, FECHA_INGRESO, TIPO_VENDEDOR) VALUES (?, ?, ?, ?, ?)";
        String updateVendedor = "UPDATE vendedores SET CODIGO_DISTRITO = ?, TIPO_VENDEDOR = ? WHERE ID_USER = ?";
        String deleteVendedor = "DELETE FROM vendedores WHERE ID_USER = ?";

        try (Connection con = ConexionBD.getConexion()) {
            con.setAutoCommit(false);

            // Actualizar usuario
            try (PreparedStatement pu = con.prepareStatement(updateUser)) {
                pu.setInt(1, p.getID_ROL());
                pu.setString(2, p.getNOMBRE1());
                pu.setString(3, p.getNOMBRE2());
                pu.setString(4, p.getAPELLIDO1());
                pu.setString(5, p.getAPELLIDO2());
                pu.setString(6, p.getCEDULA());
                pu.setString(7, p.getCORREO());
                pu.setDouble(8, p.getSALARIO());
                pu.setString(9, p.getTIPO_CONTRATO());
                pu.setBoolean(10, p.isESTADO());
                pu.setInt(11, p.getID_USER());
                pu.executeUpdate();
            }

            // Manejar vendedor
            if (p.isES_VENDEDOR()) {
                String check = "SELECT 1 FROM vendedores WHERE ID_USER = ?";
                try (PreparedStatement chk = con.prepareStatement(check)) {
                    chk.setInt(1, p.getID_USER());
                    try (ResultSet rs = chk.executeQuery()) {
                        if (rs.next()) {
                            // Existe -> actualizar
                            try (PreparedStatement pv = con.prepareStatement(updateVendedor)) {
                                pv.setString(1, p.getCODIGO_DISTRITO());
                                pv.setString(2, p.getTIPO_VENDEDOR());
                                pv.setInt(3, p.getID_USER());
                                pv.executeUpdate();
                            }
                        } else {
                            // Insertar nuevo vendedor
                            String codigo = (p.getCODIGO_VENDEDOR() == null || p.getCODIGO_VENDEDOR().isEmpty())
                                             ? generarNuevoCodigoVendedor()
                                             : p.getCODIGO_VENDEDOR();

                            try (PreparedStatement pv = con.prepareStatement(insertVendedor)) {
                                pv.setString(1, codigo);
                                pv.setInt(2, p.getID_USER());
                                pv.setString(3, p.getCODIGO_DISTRITO());
                                pv.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                                pv.setString(5, p.getTIPO_VENDEDOR());
                                pv.executeUpdate();
                            }
                        }
                    }
                }
            } else {
                // Ya no es vendedor -> eliminar
                try (PreparedStatement pv = con.prepareStatement(deleteVendedor)) {
                    pv.setInt(1, p.getID_USER());
                    pv.executeUpdate();
                }
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR USUARIO (+ VENDEDOR)
    public boolean eliminar(int idUser) {
    String updateU = "UPDATE usuarios SET estado = 0 WHERE ID_USER = ?";

    try (Connection con = ConexionBD.getConexion();
         PreparedStatement pu = con.prepareStatement(updateU)) {

        con.setAutoCommit(false);

        // Desactivar usuario
        pu.setInt(1, idUser);
        pu.executeUpdate();

        con.commit();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public List<PersonalModel> listarSoloVendedores() {
    List<PersonalModel> lista = new ArrayList<>();

    String sql = "SELECT ID_USER, NOMBRE1, APELLIDO1, CODIGO_VENDEDOR "
               + "FROM personal WHERE ES_VENDEDOR = 1";

    try (Connection con = ConexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            PersonalModel p = new PersonalModel();
            p.setID_USER(rs.getInt("ID_USER"));
            p.setNOMBRE1(rs.getString("NOMBRE1"));
            p.setAPELLIDO1(rs.getString("APELLIDO1"));
            p.setCODIGO_VENDEDOR(rs.getString("CODIGO_VENDEDOR"));
            lista.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}

    public List<PersonalModel> listarMenosVentas(int idEmpresa) {
    List<PersonalModel> lista = new ArrayList<>();

    String sql = "SELECT v.CODIGO_VENDEDOR, " +
                 "       u.NOMBRE1, u.NOMBRE2, u.APELLIDO1, u.APELLIDO2, " +
                 "       COALESCE(SUM(df.PRECIO_VENTA * df.CANTIDAD_VENTA), 0) AS TOTAL_VENTAS " +
                 "FROM vendedores v " +
                 "INNER JOIN usuarios u ON u.ID_USER = v.ID_USER " +
                 "LEFT JOIN factura f ON f.CODIGO_VENDEDOR = v.CODIGO_VENDEDOR " +
                 "LEFT JOIN detalle_factura df ON df.NUMERO_FACTURA = f.NUMERO_FACTURA " +
                 "WHERE u.ID_EMPRESA = ? AND u.estado = 1 " +
                 "GROUP BY v.CODIGO_VENDEDOR, u.NOMBRE1, u.NOMBRE2, u.APELLIDO1, u.APELLIDO2 " +
                 "ORDER BY TOTAL_VENTAS ASC " +
                 "LIMIT 3";

    try (Connection con = ConexionBD.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idEmpresa);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            PersonalModel p = new PersonalModel();
            p.setCODIGO_VENDEDOR(rs.getString("CODIGO_VENDEDOR"));
            p.setNOMBRE1(rs.getString("NOMBRE1"));
            p.setNOMBRE2(rs.getString("NOMBRE2"));
            p.setAPELLIDO1(rs.getString("APELLIDO1"));
            p.setAPELLIDO2(rs.getString("APELLIDO2"));
            p.setTOTAL_VENTAS(rs.getDouble("TOTAL_VENTAS"));
            lista.add(p);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}



}
