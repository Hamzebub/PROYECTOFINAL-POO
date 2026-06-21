/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author delac
 */
public class Usuario extends Persona {

    private String usuario;
    private String contrasena;
    private String rol;

    public Usuario() {
    }

    public Usuario(String usuario, String contrasena, String rol,
            String dni, String nombre, String telefono) {

        super(dni, nombre, telefono);
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public boolean iniciarSesion(String usuario, String contrasena) {

        return this.usuario.equals(usuario)
                && this.contrasena.equals(contrasena);
    }

    @Override
    public void mostrarInfo() {
        System.out.println(nombre);
    }
}
