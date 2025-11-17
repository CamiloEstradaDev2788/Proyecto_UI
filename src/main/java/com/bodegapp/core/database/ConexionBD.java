package com.bodegapp.core.database;

//imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    
  /*  private static final String URL = "jdbc:mysql://localhost:3306/bodegapp_db";
    private static final String USER = "CamiloEstrada";
    private static final String PASSWORD = "Ep.1028880723";
    */
    private static final String URL = "jdbc:mysql://localhost:3307/bodegapp_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static Connection conn = null;
    
    public static Connection getConexion()throws SQLException {
        if (conn == null || conn.isClosed()){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexion establecida correctamente.");  
                } catch (ClassNotFoundException | SQLException e){
                    System.out.println("Error de conexión: " + e.getMessage());
                    throw new SQLException(e);
                }
            }
        return conn;
        }
    
}
