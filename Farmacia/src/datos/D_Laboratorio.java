package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.EstadoOperacion;
import modelo.Laboratorio;
import modelo.Respuesta;


public class D_Laboratorio extends RepositorioBase implements ICRUD<Laboratorio,Laboratorio>{

    @Override
    public Respuesta<Laboratorio> guardar(Laboratorio lab) {
       Respuesta<Laboratorio> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Laboratorio_Guardar(?)}");

            cs.setString(1, lab.getMarca_Detalle());

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
    public Respuesta<Laboratorio> actualizar(Laboratorio lab) {
        Respuesta<Laboratorio> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Laboratorio_Actualizar(?,?)}");

            cs.setInt(1, lab.getMarca_Id());
            cs.setString(2, lab.getMarca_Detalle());

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
    public Respuesta<Laboratorio> eliminar(int id) {
        Respuesta<Laboratorio> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Laboratorio_Eliminar(?)}");

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
    public Respuesta<List<Laboratorio>> listar() {
        Respuesta<List<Laboratorio>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Laboratorio_Listar()}");

            rs = cs.executeQuery();
            List<Laboratorio> lst = new ArrayList<>();
            while(rs.next()) {

                lst.add(new Laboratorio(
                        rs.getInt("Laboratorio_Id"),
                        rs.getString("Laboratorio_Detalle")
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
