package datos;

import java.util.List;
import modelo.Producto;
import modelo.Respuesta;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Categoria;
import modelo.EstadoOperacion;
import modelo.Laboratorio;
import modelo.Marca;

public class D_Producto extends RepositorioBase 
        implements ICRUD_Producto<Producto,Producto>{

    @Override
    public Respuesta<Producto> guardar(Producto producto) {
        Respuesta<Producto> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Producto_Guardar(?,?,?,?,?,?,?,?)}");

            cs.setString(1, producto.getCodigo());
            cs.setString(2, producto.getNombre());
            cs.setDouble(3, producto.getPrecio());
            cs.setInt(4, producto.getStock());
            if(producto.getFechaVencimiento()!=null){
                cs.setObject(5, producto.getFechaVencimiento());
            }else{
                cs.setNull(5, java.sql.Types.DATE);
            }
            
            cs.setInt(6, producto.getLaboratorio().getLaboratorio_Id());
            cs.setInt(7, producto.getMarca().getMarca_Id());
            cs.setInt(8, producto.getCategoria().getCategoria_Id());

            rs = cs.executeQuery();

            if (rs.next()) {

                resultado.setEstado(
                        EstadoOperacion.fromCodigo(
                                rs.getInt("Respuesta")));

                resultado.setMensaje(
                        rs.getString("Mensaje"));
            }

        } catch (SQLException e) {

            resultado.setEstado(EstadoOperacion.ERROR);
            resultado.setMensaje(e.getMessage());

        } finally {

            cerrar(rs, cs, cn);
        }

        return resultado;
    }

    @Override
    public Respuesta<Producto> actualizar(Producto producto) {
        Respuesta<Producto> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Producto_Actualizar(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, producto.getProducto_Id());
            cs.setString(2, producto.getCodigo());
            cs.setString(3, producto.getNombre());
            cs.setDouble(4, producto.getPrecio());
            cs.setInt(5, producto.getStock());
            if(producto.getFechaVencimiento()!=null){
                cs.setObject(6, producto.getFechaVencimiento());
            }else{
                cs.setNull(6, java.sql.Types.DATE);
            }
            
            cs.setInt(7, producto.getLaboratorio().getLaboratorio_Id());
            cs.setInt(8, producto.getMarca().getMarca_Id());
            cs.setInt(9, producto.getCategoria().getCategoria_Id());

            rs = cs.executeQuery();

            if (rs.next()) {

                resultado.setEstado(
                        EstadoOperacion.fromCodigo(
                                rs.getInt("Respuesta")));

                resultado.setMensaje(
                        rs.getString("Mensaje"));
            }

        } catch (SQLException e) {

            resultado.setEstado(EstadoOperacion.ERROR);
            resultado.setMensaje(e.getMessage());

        } finally {

            cerrar(rs, cs, cn);
        }

        return resultado;
    }

    @Override
    public Respuesta<Producto> eliminar(int id) {
        Respuesta<Producto> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Producto_Eliminar(?)}");

            cs.setInt(1,id);

            rs = cs.executeQuery();

            if (rs.next()) {

                resultado.setEstado(
                        EstadoOperacion.fromCodigo(
                                rs.getInt("Respuesta")));

                resultado.setMensaje(
                        rs.getString("Mensaje"));
            }

        } catch (SQLException e) {

            resultado.setEstado(EstadoOperacion.ERROR);
            resultado.setMensaje(e.getMessage());

        } finally {

            cerrar(rs, cs, cn);
        }

        return resultado;
    }

    @Override
    public Respuesta<List<Producto>> listar() {
        Respuesta<List<Producto>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Producto_Listar()}");

            rs = cs.executeQuery();
            List<Producto> lst = new ArrayList<>();
            while(rs.next()) {
                Marca _marca = new Marca(
                        rs.getInt("Marca_Id"),
                        rs.getString("Marca_Detalle")
                );
                Categoria _categoria=new Categoria(
                        rs.getInt("Categoria_Id"),
                        rs.getString("Categoria_Detalle")
                );
                Laboratorio _laboratorio=new Laboratorio(
                        rs.getInt("Laboratorio_Id"),
                        rs.getString("Laboratorio_Detalle")
                );
                
                Producto _producto=new Producto();
                _producto.setProducto_Id(rs.getInt("Producto_Id"));
                _producto.setCodigo(rs.getString("Codigo"));
                _producto.setNombre(rs.getString("Nombre"));
                _producto.setPrecio(rs.getDouble("Precio"));
                _producto.setStock(rs.getInt("Stock"));
                _producto.setFechaVencimiento(rs.getObject("FechaVencimiento", LocalDate.class));
                _producto.setCategoria(_categoria);
                _producto.setLaboratorio(_laboratorio);
                _producto.setMarca(_marca);
                
                lst.add(_producto);
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

    @Override
    public Respuesta<Producto> actualizarSTOCK(Producto entidad) {
        Respuesta<Producto> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Producto_ActualizarStock(?,?)}");
            int fila=1;
            cs.setInt(fila++,entidad.getProducto_Id());
            cs.setInt(fila++,entidad.getStock());

            rs = cs.executeQuery();

            if (rs.next()) {

                resultado.setEstado(
                        EstadoOperacion.fromCodigo(
                                rs.getInt("Respuesta")));

                resultado.setMensaje(
                        rs.getString("Mensaje"));
            }

        } catch (SQLException e) {

            resultado.setEstado(EstadoOperacion.ERROR);
            resultado.setMensaje(e.getMessage());

        } finally {

            cerrar(rs, cs, cn);
        }

        return resultado;
    }
    
}
