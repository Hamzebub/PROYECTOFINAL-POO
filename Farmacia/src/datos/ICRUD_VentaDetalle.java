package datos;

import java.util.List;
import modelo.Respuesta;

/**
 *
 * @author Migue
 */
public interface ICRUD_VentaDetalle<T,R> extends ICRUD<T,R>{
    Respuesta<List<R>> listarxVentaID(int id);
    
}
