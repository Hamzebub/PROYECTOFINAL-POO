package datos;

import java.math.BigDecimal;
import modelo.Respuesta;
import modelo.VentaDetalle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.EstadoOperacion;
import modelo.Producto;
/**
 *
 * @author Migue
 */
public class D_VentaDetalle extends RepositorioBase
implements ICRUD_VentaDetalle<VentaDetalle,VentaDetalle>{
               
    @Override
    public Respuesta<VentaDetalle> guardar(VentaDetalle ventaDetalle) {
       Respuesta<VentaDetalle> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_VentaDetalle_Guardar(?,?,?,?,?)}");
            int fila=1;    
            cs.setInt(fila++, ventaDetalle.getVenta_Id());
            cs.setInt(fila++, ventaDetalle.getProducto().getProducto_Id());
            cs.setInt(fila++, ventaDetalle.getCantidad());
            cs.setObject(fila++,ventaDetalle.getPrecioUnitario());
            cs.setObject(fila++,ventaDetalle.getSubtotal());
            

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
    public Respuesta<VentaDetalle> actualizar(VentaDetalle ventaDetalle) {
        Respuesta<VentaDetalle> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_VentaDetalle_Actualizar(?,?,?,?,?,?)}");

            int fila=1;    
            cs.setInt(fila++, ventaDetalle.getVentaDetalle_Id());
            cs.setInt(fila++, ventaDetalle.getVenta_Id());
            cs.setInt(fila++, ventaDetalle.getProducto().getProducto_Id());
            cs.setInt(fila++, ventaDetalle.getCantidad());
            cs.setObject(fila++,ventaDetalle.getPrecioUnitario());
            cs.setObject(fila++,ventaDetalle.getSubtotal());
            

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
    public Respuesta<VentaDetalle> eliminar(int id) {
        Respuesta<VentaDetalle> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_VentaDetalle_Eliminar(?)}");

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
    public Respuesta<List<VentaDetalle>> listar() {
        Respuesta<List<VentaDetalle>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_VentaDetalle_Listar()}");

            rs = cs.executeQuery();
            List<VentaDetalle> lst = new ArrayList<>();
            while(rs.next()) {
                VentaDetalle vd = new VentaDetalle();
                vd.setVentaDetalle_Id(rs.getInt("VentaDetalle_Id"));
                vd.setVenta_Id(rs.getInt("Venta_Id"));
                vd.setCantidad(rs.getInt("Cantidad"));
                vd.setPrecioUnitario(new BigDecimal(rs.getObject("SubTotal").toString()));
                vd.setSubtotal(new BigDecimal(rs.getObject("SubTotal").toString()));

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
    public Respuesta<List<VentaDetalle>> listarxVentaID(int id) {
        Respuesta<List<VentaDetalle>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_VentaDetalle_ListarxID(?)}");
            cs.setInt(1,id);

            rs = cs.executeQuery();
            List<VentaDetalle> lst = new ArrayList<>();
            while(rs.next()) {
                VentaDetalle vd = new VentaDetalle();
                vd.setVentaDetalle_Id(rs.getInt("VentaDetalle_Id"));
                vd.setVenta_Id(rs.getInt("Venta_Id"));
                vd.setCantidad(rs.getInt("Cantidad"));
                vd.setPrecioUnitario(new BigDecimal(rs.getObject("SubTotal").toString()));
                vd.setSubtotal(new BigDecimal(rs.getObject("SubTotal").toString()));
                
                Producto p = new Producto();
                p.setProducto_Id(rs.getInt("Producto_Id"));
                p.setNombre(rs.getString("Nombre"));
                
                vd.setProducto(p);
                lst.add(vd);

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
