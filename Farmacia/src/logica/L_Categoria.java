package logica;

import datos.D_Categoria;
import datos.ICRUD;
import java.util.List;
import modelo.Categoria;
import modelo.EstadoOperacion;
import modelo.Respuesta;

public class L_Categoria {
    private final ICRUD<Categoria,Categoria> obj;
    
    public L_Categoria() {
        this.obj = new D_Categoria();
    }
    
    public Respuesta<Categoria> guardar(Categoria categoria) {
      
        if (categoria == null) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "La categoria no puede ser nula"
            );
        }

        if (categoria.getCategoria_Detalle()== null ||
            categoria.getCategoria_Detalle().trim().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe ingresar el nombre de la categoria"
            );
        }

        if (categoria.getCategoria_Detalle().length() > 100) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Nombre demasiado largo. El maximo es de 100 caracteres."
            );
        }

        
        return obj.guardar(categoria);
    }
    
    
    public Respuesta<Categoria> actualizar(Categoria categoria) {

        if (categoria.getCategoria_Id()<= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.actualizar(categoria);
    }
    
    public Respuesta<Categoria> eliminar(int id) {

        if (id <= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.eliminar(id);
    }
    
    public Respuesta<List<Categoria>> listar() {
        return obj.listar();
    }
}
