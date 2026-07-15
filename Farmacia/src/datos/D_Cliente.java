/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.EstadoOperacion;
import modelo.Respuesta;
import modelo.TipoDocumento;

/**
 *
 * @author delac
 */
public class D_Cliente extends RepositorioBase 
        implements ICRUD<Cliente,Cliente> {

    @Override
    public Respuesta<Cliente> guardar(Cliente entidad) {
        Respuesta<Cliente> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Cliente_Guardar(?,?,?,?)}");

            cs.setInt(1, entidad.getTipoDocumento().getTipoDocumento_Id());
            cs.setString(2, entidad.getDni());
            cs.setString(3, entidad.getNombre());
            cs.setString(4, entidad.getTelefono());

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
    public Respuesta<Cliente> actualizar(Cliente entidad) {
        Respuesta<Cliente> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Cliente_Actualizar(?,?,?,?,?)}");

            cs.setInt(1, entidad.getCliente_Id());
            cs.setInt(2, entidad.getTipoDocumento().getTipoDocumento_Id());
            cs.setString(3, entidad.getDni());
            cs.setString(4, entidad.getNombre());
            cs.setString(5, entidad.getTelefono());

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
    public Respuesta<Cliente> eliminar(int id) {
        Respuesta<Cliente> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Cliente_Eliminar(?)}");

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
    public Respuesta<List<Cliente>> listar() {
        Respuesta<List<Cliente>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Cliente_Listar()}");

            rs = cs.executeQuery();
            List<Cliente> lst = new ArrayList<>();
            while(rs.next()) {
                TipoDocumento tipo = new TipoDocumento();
                tipo.setTipoDocumento_Id(rs.getInt("TipoDocumento_Id"));
                tipo.setDocumentoDetalle(rs.getString("DocumentoDetalle"));
                tipo.setAbreviatura(rs.getString("Abreviatura"));
                tipo.setDefecto(rs.getInt("Defecto"));
                
                lst.add(new Cliente(
                    rs.getInt("Cliente_Id"),
                    tipo,
                    rs.getInt("PuntosAcumulados"),
                    rs.getString("NumeroDocumento"),
                    rs.getString("Nombre"),
                    rs.getString("Telefono"),
                    rs.getInt("Activo")
                ));
            }
        
            resultado.setDatos(lst);
            resultado.setEstado(EstadoOperacion.EXITO);

        } catch (SQLException e) {

            resultado.setEstado(EstadoOperacion.ERROR);
            resultado.setMensaje(e.getMessage());

        } finally {

            cerrar(rs, cs, cn);
        }

        return resultado;
    }
    
}
