/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.D_Cliente;
import datos.ICRUD;
import java.util.List;
import modelo.Cliente;
import modelo.EstadoOperacion;
import modelo.Respuesta;

/**
 *
 * @author delac
 */
public class L_Cliente {
    private final ICRUD<Cliente,Cliente> obj;
    
    public L_Cliente() {
        this.obj = new D_Cliente();
    }
    
    public Respuesta<Cliente> guardar(Cliente cliente) {
      
        if (cliente == null) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe agregar un cliente"
            );
        }
        
        if (cliente.getDni()== null ||
            cliente.getDni().trim().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe ingresar el N° de documento del cliente"
            );
        }
        
        if (cliente.getTipoDocumento().getTipoDocumento_Id() == 0||
            cliente.getDni().trim().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe seleccionar un tipo de documento"
            );
        }

        if (cliente.getNombre()== null ||
            cliente.getNombre().trim().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe ingresar el nombre del cliente"
            );
        }

        /*if (cliente.getCategoria_Detalle().length() > 100) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Nombre demasiado largo. El maximo es de 100 caracteres."
            );
        }*/

        
        return obj.guardar(cliente);
    }
    
    
    public Respuesta<Cliente> actualizar(Cliente cliente) {

        if (cliente.getId()<= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.actualizar(cliente);
    }
    
    public Respuesta<Cliente> eliminar(int id) {

        if (id <= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.eliminar(id);
    }
    
    public Respuesta<List<Cliente>> listar() {
        return obj.listar();
    }
}
