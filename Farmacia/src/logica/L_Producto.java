package logica;

import datos.D_Producto;
import datos.ICRUD;
import java.util.List;
import modelo.EstadoOperacion;
import modelo.Producto;
import modelo.Respuesta;

/**
 *
 * @author delac
 */
public class L_Producto {
   private final ICRUD<Producto,Producto> obj;
    
    public L_Producto() {
        this.obj = new D_Producto();
    }
    
    public Respuesta<Producto> guardar(Producto producto) {
      
        if (producto == null) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "La producto no puede ser nula"
            );
        }

        if (producto.getNombre()== null ||
            producto.getNombre().trim().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe ingresar el nombre de la producto"
            );
        }

        if (producto.getNombre().length() > 100) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Nombre demasiado largo. El maximo es de 100 caracteres."
            );
        }

        
        return obj.guardar(producto);
    }
    
    public Respuesta<Producto> actualizar(Producto producto) {

        if (producto.getProducto_Id()<= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.actualizar(producto);
    }
    
    public Respuesta<Producto> eliminar(int id) {

        if (id <= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.eliminar(id);
    }
    
    public Respuesta<List<Producto>> listar() {
        return obj.listar();
    }
     
}
