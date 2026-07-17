/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.D_Usuario;
import java.util.List;
import modelo.EstadoOperacion;
import modelo.Respuesta;
import modelo.Usuario;

/**
 *
 * @author delac
 */
public class L_Usuario {

    private final D_Usuario obj;

    public L_Usuario() {
        obj = new D_Usuario();
    }
    
    public Respuesta<Usuario> guardar(Usuario usuario) {
      
        if (usuario == null) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe agregar un cliente"
            );
        }
        
        if (usuario.getDni()== null ||
            usuario.getDni().trim().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe ingresar el N° de documento del cliente"
            );
        }
        
        if (usuario.getTipoDocumento().getTipoDocumento_Id() == 0||
            usuario.getDni().trim().isEmpty()) {

            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "Debe seleccionar un tipo de documento"
            );
        }

        if (usuario.getNombre()== null ||
            usuario.getNombre().trim().isEmpty()) {

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

        
        return obj.guardar(usuario);
    }
    
    public Respuesta<Usuario> actualizar(Usuario usuario) {

        if (usuario.getUsuario_Id()<= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.actualizar(usuario);
    }
    
    public Respuesta<Usuario> eliminar(int id) {

        if (id <= 0) {
            return new Respuesta<>(
                EstadoOperacion.ERROR,
                "ID inválido"
            );
        }

        return obj.eliminar(id);
    }
    
    public Respuesta<List<Usuario>> listar() {
        return obj.listar();
    }

    /*public Respuesta<Usuario> login(String usuario, String clave) {

        if (usuario.trim().isEmpty()) {
            return new Respuesta<>(
                    EstadoOperacion.ERROR,
                    "Ingrese el usuario."
            );
        }

        if (clave.trim().isEmpty()) {
            return new Respuesta<>(
                    EstadoOperacion.ERROR,
                    "Ingrese la contraseña."
            );
        }

        return obj.login(usuario, clave);
    }*/

}
