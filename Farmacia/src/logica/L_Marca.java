package logica;

import datos.D_Marca;
import datos.ICRUD;
import java.util.List;
import modelo.EstadoOperacion;
import modelo.Marca;
import modelo.Respuesta;


public class L_Marca {
    private final ICRUD<Marca,Marca> obj;
    
    public L_Marca() {
        this.obj = new D_Marca();
    }
    
    public Respuesta<Marca> guardar(Marca marca) {
      
        if (marca == null) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "La marca no puede ser nula"
            );
        }

        if (marca.getMarca_Detalle()== null ||
            marca.getMarca_Detalle().trim().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe ingresar el nombre de la marca"
            );
        }

        if (marca.getMarca_Detalle().length() > 100) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Nombre demasiado largo. El maximo es de 100 caracteres."
            );
        }

        
        return obj.guardar(marca);
    }
    
    public Respuesta<Marca> actualizar(Marca marca) {

        if (marca.getMarca_Id()<= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.actualizar(marca);
    }
    
    public Respuesta<Marca> eliminar(int id) {

        if (id <= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.eliminar(id);
    }
    
    public Respuesta<List<Marca>> listar() {
        return obj.listar();
    }
    
}
