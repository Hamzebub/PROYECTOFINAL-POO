package datos;

import modelo.Respuesta;

/**
 *
 * @author Migue
 */
public interface ICRUD_Producto <T,R> extends ICRUD<T,R> {
    Respuesta<R> actualizarSTOCK(T entidad);
}
