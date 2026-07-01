package datos;

import java.util.List;
import modelo.Respuesta;

/**
 *
 * @author Migue
 */
public interface ICRUD<T,R> {
    Respuesta<R> guardar(T entidad);
    Respuesta<R> actualizar(T entidad);
    Respuesta<R> eliminar(int id);
    Respuesta<List<R>> listar();
}
