/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.D_Usuario;
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

    public Usuario iniciarSesion(String usuario,
                                 String contrasena) {

        return usuarioDAO.validarLogin(usuario, contrasena);
    }
    
}
