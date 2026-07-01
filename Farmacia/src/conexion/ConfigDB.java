package conexion;

import java.io.InputStream;
import java.util.Properties;


public class ConfigDB {
    private static Properties props = new Properties();
    
    static {
        try (InputStream input =
             ConfigDB.class.getClassLoader()
             .getResourceAsStream("db.properties")) {

            props.load(input);
            
        } catch (Exception e) {
            throw new RuntimeException("Error cargando config", e);
        }
    }
    
    public static String getConnectionString() {
        return props.getProperty("url");
    }

    public static String getUser() {
        return props.getProperty("user");
    }

    public static String getPassword() {
        return props.getProperty("password");
    }
    
    
}
