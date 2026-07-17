package logica;

import datos.D_VentaDetalle;
import datos.ICRUD_VentaDetalle;
import java.util.List;
import modelo.EstadoOperacion;
import modelo.Respuesta;
import modelo.VentaDetalle;

/**
 *
 * @author Migue
 */
public class L_VentaDetalle {
    private final ICRUD_VentaDetalle<VentaDetalle,VentaDetalle> obj;
    
    public L_VentaDetalle() {
        this.obj = new D_VentaDetalle();
    }
    
    public Respuesta<VentaDetalle> guardar(VentaDetalle ventaDetalle) {
      
        if (ventaDetalle == null) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "La ventaDetalle no puede ser nula"
            );
        }

               
        return obj.guardar(ventaDetalle);
    }
    
    public Respuesta<VentaDetalle> actualizar(VentaDetalle ventaDetalle) {

        if (ventaDetalle.getVentaDetalle_Id()<= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.actualizar(ventaDetalle);
    }
    
    public Respuesta<VentaDetalle> eliminar(int id) {

        if (id <= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.eliminar(id);
    }
    
    public Respuesta<List<VentaDetalle>> listar() {
        return obj.listar();
    }
    
    public Respuesta<List<VentaDetalle>> listarxVentaID(int id) {
        return obj.listarxVentaID(id);
    }
    
}
