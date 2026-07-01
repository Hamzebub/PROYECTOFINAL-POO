/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import conexion.Conexion;
import modelo.Usuario;

/**
 *
 * @author delac
 */
public class D_Usuario {
    
    public Usuario validarLogin(String usuario, String contrasena) {
        
        Usuario usu = null;

    try {

        Connection cn = Conexion.getConexion();

        CallableStatement cs = cn.prepareCall("{call sp_LoginUsuario(?,?)}");

        cs.setString(1, usuario);
        cs.setString(2, contrasena);

        ResultSet rs = cs.executeQuery();

        if(rs.next()){

            usu = new Usuario();

            usu.setUsuario(rs.getString("Usuario"));

            usu.setRol(rs.getString("Rol"));
        }

    } catch(Exception e){
        e.printStackTrace();
    }

    return usu;
    }

    /*public boolean registrar(Usuario usuario) {
        // INSERT ...
        
        return ;
    }*/
    
}
