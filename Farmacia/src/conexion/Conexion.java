/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author delac
 */
public class Conexion {
    
    private static final String URL =
            "jdbc:sqlserver://localhost:1433;databaseName=FarmaciaDB;encrypt=true;trustServerCertificate=true";

    private static final String USER = "sa";
    private static final String PASSWORD = "123456";

    public static Connection getConexion() {

        Connection cn = null;

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            cn = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Error de conexión: "
                    + e.getMessage());

        }

        return cn;
    }
    
}
