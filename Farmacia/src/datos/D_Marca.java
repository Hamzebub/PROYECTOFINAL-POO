package datos;

import java.util.List;
import modelo.Marca;
import modelo.Respuesta;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.EstadoOperacion;

public class D_Marca extends RepositorioBase 
        implements ICRUD<Marca,Marca>{

    @Override
    public Respuesta<Marca> guardar(Marca marca) {
       Respuesta<Marca> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Marca_Guardar(?)}");

            cs.setString(1, marca.getMarca_Detalle());

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
    public Respuesta<Marca> actualizar(Marca marca) {
        Respuesta<Marca> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Marca_Actualizar(?,?)}");

            cs.setInt(1, marca.getMarca_Id());
            cs.setString(2, marca.getMarca_Detalle());

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
    public Respuesta<Marca> eliminar(int id) {
        Respuesta<Marca> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Marca_Eliminar(?)}");

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
    public Respuesta<List<Marca>> listar() {
        Respuesta<List<Marca>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Marca_Listar()}");

            rs = cs.executeQuery();
            List<Marca> lst = new ArrayList<>();
            while(rs.next()) {

                lst.add(new Marca(
                        rs.getInt("Marca_Id"),
                        rs.getString("Marca_Detalle")
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
    
}
