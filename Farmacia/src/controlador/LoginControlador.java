/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;

/**
 *
 * @author delac
 */
public class LoginControlador {
    
    private UsuarioDAO usuarioDAO;

    public LoginControlador() {
        usuarioDAO = new UsuarioDAO();
    }

    public Usuario iniciarSesion(String usuario,
                                 String contrasena) {

        return usuarioDAO.validarLogin(usuario, contrasena);
    }
    
}
