/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import conexion.Conexion;
import java.util.ArrayList;
import java.util.List;
import modelo.EstadoOperacion;
import modelo.Respuesta;
import modelo.TipoDocumento;
import modelo.Usuario;

/**
 *
 * @author delac
 */
public class D_Usuario extends RepositorioBase implements IUSUARIO {

    @Override
    public Respuesta<Usuario> guardar(Usuario entidad) {
        Respuesta<Usuario> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Usuario_Guardar(?,?,?,?,?,?,?)}");

            cs.setInt(1, entidad.getTipoDocumento().getTipoDocumento_Id());
            cs.setString(2, entidad.getDni());
            cs.setString(3, entidad.getNombre());
            cs.setString(4, entidad.getTelefono());
            cs.setString(5, entidad.getUsuario());
            cs.setString(6, entidad.getContrasena());
            cs.setString(7, entidad.getRol());
            
            rs = cs.executeQuery();

            if (rs.next()) {

                resultado.setEstado(
                        EstadoOperacion.fromCodigo(
                                rs.getInt("Respuesta")));

                resultado.setMensaje(
                        rs.getString("Mensaje"));
            }

        } catch (Exception e) {

            resultado.setEstado(EstadoOperacion.ERROR);
            resultado.setMensaje(e.getMessage());

        } finally {

            cerrar(rs, cs, cn);
        }

        return resultado;    
    }

    @Override
    public Respuesta<Usuario> actualizar(Usuario entidad) {
        Respuesta<Usuario> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Usuario_Actualizar(?,?,?,?,?,?,?,?)}");

            cs.setInt(1, entidad.getUsuario_Id());
            cs.setInt(2, entidad.getTipoDocumento().getTipoDocumento_Id());
            cs.setString(3, entidad.getDni());
            cs.setString(4, entidad.getNombre());
            cs.setString(5, entidad.getTelefono());
            cs.setString(6, entidad.getUsuario());
            cs.setString(7, entidad.getContrasena());
            cs.setString(8, entidad.getRol());
            
            rs = cs.executeQuery();

            if (rs.next()) {

                resultado.setEstado(
                        EstadoOperacion.fromCodigo(
                                rs.getInt("Respuesta")));

                resultado.setMensaje(
                        rs.getString("Mensaje"));
            }

        } catch (Exception e) {

            resultado.setEstado(EstadoOperacion.ERROR);
            resultado.setMensaje(e.getMessage());

        } finally {

            cerrar(rs, cs, cn);
        }

        return resultado;
    }

    @Override
    public Respuesta<List<Usuario>> listar() {
        Respuesta<List<Usuario>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Usuario_Listar()}");

            rs = cs.executeQuery();
            List<Usuario> lst = new ArrayList<>();
            while(rs.next()) {
                TipoDocumento tipo = new TipoDocumento();
                tipo.setTipoDocumento_Id(rs.getInt("TipoDocumento_Id"));
                tipo.setDocumentoDetalle(rs.getString("DocumentoDetalle"));
                tipo.setAbreviatura(rs.getString("Abreviatura"));
                tipo.setDefecto(rs.getInt("Defecto"));
                
                lst
                        .add(new Usuario(
                        rs.getInt("Usuario_Id"),
                        tipo,
                        rs.getString("Usuario"),
                        rs.getString("Clave"),
                        rs.getString("Rol"),
                        rs.getString("NumeroDocumento"),
                        rs.getString("Nombre"),
                        rs.getString("Telefono"),
                        rs.getInt("Activo")
                    ));
            }
        
            resultado.setDatos(lst);
            resultado.setEstado(EstadoOperacion.EXITO);

        } catch (Exception e) {

            resultado.setEstado(EstadoOperacion.ERROR);
            resultado.setMensaje(e.getMessage());

        } finally {

            cerrar(rs, cs, cn);
        }

        return resultado;
    }

    @Override
    public Respuesta<Usuario> eliminar(int id) {
        Respuesta<Usuario> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Usuario_Eliminar(?)}");

            cs.setInt(1,id);

            rs = cs.executeQuery();

            if (rs.next()) {

                resultado.setEstado(
                        EstadoOperacion.fromCodigo(
                                rs.getInt("Respuesta")));

                resultado.setMensaje(
                        rs.getString("Mensaje"));
            }

        } catch (Exception e) {

            resultado.setEstado(EstadoOperacion.ERROR);
            resultado.setMensaje(e.getMessage());

        } finally {

            cerrar(rs, cs, cn);
        }

        return resultado;        
    }

    
    @Override
    public Respuesta<Usuario> login(String usuario, String contrasena) {

        Respuesta<Usuario> resultado = new Respuesta<>();

        try {

            Connection cn = Conexion.getConexion();

            CallableStatement cs = cn.prepareCall("{CALL sp_LoginUsuario(?,?)}");

            cs.setString(1, usuario);
            cs.setString(2, contrasena);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {

                Usuario usu = new Usuario(
                    rs.getInt("Usuario_Id"),
                    null,
                    rs.getString("Usuario"),
                    rs.getString("Clave"),
                    rs.getString("Rol"),
                    rs.getString("NumeroDocumento"),
                    rs.getString("Nombre"),
                    null,
                    rs.getInt("Activo")
                );

                resultado.setDatos(usu);
                resultado.setEstado(EstadoOperacion.EXITO);

            } else {

                resultado.setEstado(EstadoOperacion.ERROR);
                resultado.setMensaje("Usuario o contraseña incorrectos.");
            }

        } catch (Exception e) {

            resultado.setEstado(EstadoOperacion.ERROR);
            resultado.setMensaje(e.getMessage());

        }

        return resultado;
    }
    
    @Override
    public Respuesta<Boolean> existeUsuario(String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Respuesta<Boolean> cambiarClave(int usuarioId, String nuevaClave) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
