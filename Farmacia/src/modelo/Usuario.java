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
    private int usuario_id;
    private String usuario;
    private String contrasena;
    private String rol;
    private int activo;

    public Usuario() {
    }
    
    public Usuario(int usuario_id,TipoDocumento tipoDocumento,String usuario, String contrasena, String rol,
            String dni, String nombre, String telefono,int activo) {

        super(dni, nombre, telefono);
        this.usuario_id = usuario_id;
        this.tipoDocumento = tipoDocumento;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.activo = activo;
    }
    
    public Usuario(int usuario_id,TipoDocumento tipoDocumento,String usuario, String contrasena, String rol,
            String dni, String nombre, String telefono) {

        super(dni, nombre, telefono);
        this.usuario_id = usuario_id;
        this.tipoDocumento = tipoDocumento;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    
    public int getUsuario_Id() {
        return usuario_id;
    }

    public void setUsuario_Id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public String getUsuario() {
    return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public void mostrarInfo() {
        System.out.println(nombre);
    }
}
