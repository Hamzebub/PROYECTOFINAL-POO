package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author delac
 */
public class Conexion {
    
    public static Connection getConexion() {

        Connection cn = null;

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            cn = DriverManager.getConnection(
                    ConfigDB.getConnectionString(),
                    ConfigDB.getUser(),
                    ConfigDB.getPassword());

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Error de conexión: "
                    + e.getMessage());

        }

        return cn;
    }
    
}
