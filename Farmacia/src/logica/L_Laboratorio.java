package logica;
import datos.D_Laboratorio;
import datos.ICRUD;
import java.util.List;
import modelo.EstadoOperacion;
import modelo.Laboratorio;
import modelo.Respuesta;

public class L_Laboratorio {
    private final ICRUD<Laboratorio,Laboratorio> obj;
    
    public L_Laboratorio() {
        this.obj = new D_Laboratorio();
    }
    
    public Respuesta<Laboratorio> guardar(Laboratorio lab) {
      
        if (lab == null) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "El laboratorio no puede ser nulo"
            );
        }

        if (lab.getMarca_Detalle()== null ||
            lab.getMarca_Detalle().trim().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe ingresar el nombre del laboratorio"
            );
        }

        if (lab.getMarca_Detalle().length() > 100) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Nombre demasiado largo. El maximo es de 100 caracteres."
            );
        }

        
        return obj.guardar(lab);
    }
    
    public Respuesta<Laboratorio> actualizar(Laboratorio lab) {

        if (lab.getMarca_Id()<= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.actualizar(lab);
    }
    
    public Respuesta<Laboratorio> eliminar(int id) {

        if (id <= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.eliminar(id);
    }
    
    public Respuesta<List<Laboratorio>> listar() {
        return obj.listar();
    }
}
