/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.D_Usuario;
import modelo.EstadoOperacion;
import modelo.Respuesta;
import modelo.Usuario;

/**
 *
 * @author delac
 */
public class L_Login {
    
    private D_Usuario usuarioDAO;

    public L_Login() {
        usuarioDAO = new D_Usuario();
    }

    public Respuesta<Usuario> login(String usuario, String clave) {

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

        return usuarioDAO.login(usuario, clave);
    }
    
}
