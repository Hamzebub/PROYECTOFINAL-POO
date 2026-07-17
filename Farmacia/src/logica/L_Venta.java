package logica;

import datos.D_Venta;
import datos.ICRUD;
import java.util.List;
import modelo.EstadoOperacion;
import modelo.Respuesta;
import modelo.Venta;

/**
 *
 * @author delac
 */
public class L_Venta {
    private final ICRUD<Venta,Venta> obj;
    
    public L_Venta() {
        this.obj = new D_Venta();
    }
    
    public Respuesta<Venta> guardar(Venta venta) {
      
        if (venta == null) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "La venta no puede ser nula"
            );
        }

        if (venta.getCliente().getId()==0) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe seleccionar un cliete valido"
            );
        }

        if (venta.getVentaDetalle().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe registrar minimo un prodcuto."
            );
        }

        
        return obj.guardar(venta);
    }
    
    public Respuesta<Venta> actualizar(Venta venta) {

        if (venta.getVenta_Id()<= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }
        
        if (venta.getCliente().getId()==0) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe seleccionar un cliete valido"
            );
        }

        if (venta.getVentaDetalle().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe registrar minimo un prodcuto."
            );
        }

        return obj.actualizar(venta);
    }
    
    public Respuesta<Venta> eliminar(int id) {

        if (id <= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.eliminar(id);
    }
    
    public Respuesta<List<Venta>> listar() {
        return obj.listar();
    }
    
}
