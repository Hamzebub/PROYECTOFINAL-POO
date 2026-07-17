/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datos;

import modelo.Respuesta;
import modelo.Usuario;

/**
 *
 * @author delac
 */
public interface IUSUARIO extends ICRUD<Usuario, Usuario> {

    Respuesta<Usuario> login(String usuario, String clave);

    Respuesta<Boolean> existeUsuario(String usuario);

    Respuesta<Boolean> cambiarClave(int usuarioId, String nuevaClave);

}
