package datos;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.*;

public abstract class RepositorioBase {
     protected Connection getConexion() {
        return Conexion.getConexion();
    }
     
     protected void cerrar(ResultSet rs,
                          Statement st,
                          Connection cn) {

        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
        }

        try {
            if (st != null) st.close();
        } catch (SQLException e) {
        }

        try {
            if (cn != null) cn.close();
        } catch (SQLException e) {
        }
    }
}
