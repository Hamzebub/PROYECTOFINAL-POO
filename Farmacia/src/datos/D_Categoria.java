package datos;

import java.util.List;
import modelo.Categoria;
import modelo.Respuesta;
import java.sql.*;
import java.util.ArrayList;
import modelo.EstadoOperacion;

public class D_Categoria extends RepositorioBase 
        implements ICRUD<Categoria,Categoria> {

    @Override
    public Respuesta<Categoria> guardar(Categoria entidad) {
        Respuesta<Categoria> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Categoria_Guardar(?)}");

            cs.setString(1, entidad.getCategoria_Detalle());

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
    public Respuesta<Categoria> actualizar(Categoria entidad) {
        Respuesta<Categoria> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Categoria_Actualizar(?,?)}");

            cs.setInt(1, entidad.getCategoria_Id());
            cs.setString(2, entidad.getCategoria_Detalle());

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
    public Respuesta<Categoria> eliminar(int id) {
        Respuesta<Categoria> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Categoria_Eliminar(?)}");

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
    public Respuesta<List<Categoria>> listar() {
        Respuesta<List<Categoria>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Categoria_Listar()}");

            rs = cs.executeQuery();
            List<Categoria> lst = new ArrayList<>();
            while(rs.next()) {

                lst.add(new Categoria(
                        rs.getInt("Categoria_Id"),
                        rs.getString("Categoria_Detalle")
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
