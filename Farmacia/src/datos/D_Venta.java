package datos;

import java.math.BigDecimal;
import modelo.Venta;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.EstadoOperacion;
import modelo.EstadoVenta;
import modelo.Respuesta;
import modelo.TipoDocumentoVenta;
/**
 *
 * @author Migue
 */
public class D_Venta extends RepositorioBase 
        implements ICRUD<Venta,Venta> {
    
    @Override
    public Respuesta<Venta> guardar(Venta venta) {
        Respuesta<Venta> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Venta_Guardar(?,?,?,?,?,?)}");

            cs.setString(1, venta.getSerie());
            if(venta.getFechaCompleta()!=null){
                cs.setObject(2, venta.getFechaCompleta());
            }else{
                cs.setObject(2, LocalDateTime.now());
            }
                        
            cs.setInt(3, venta.getTipoDocumentoVenta().getTipoDocumentoVenta_Id());
            cs.setObject(4, venta.getTotal());
            
            
            cs.setInt(5, venta.getCliente().getCliente_Id());
            //cs.setInt(6, venta.getUsuario().get);
            cs.setInt(6, 1);

            rs = cs.executeQuery();

            if (rs.next()) {

                resultado.setEstado(
                        EstadoOperacion.fromCodigo(
                                rs.getInt("Respuesta")));

                resultado.setMensaje(
                        rs.getString("Mensaje"));
                resultado.setId(rs.getInt("Id"));
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
    public Respuesta<Venta> actualizar(Venta venta) {
        Respuesta<Venta> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Venta_Actualizar(?,?,?,?,?,?,?)}");
            
            cs.setInt(1, venta.getVenta_Id());
            cs.setString(2, venta.getSerie());
            if(venta.getFechaCompleta()!=null){
                cs.setObject(3, venta.getFechaCompleta());
            }else{
                cs.setObject(3, LocalDateTime.now());
            }
                        
            cs.setInt(4, venta.getTipoDocumentoVenta().getTipoDocumentoVenta_Id());
            cs.setObject(5, venta.getTotal());
            
            
            cs.setInt(6, venta.getCliente().getCliente_Id());
            //cs.setInt(6, venta.getUsuario().get);
            cs.setInt(7, 1);


            rs = cs.executeQuery();

            if (rs.next()) {

                resultado.setEstado(
                        EstadoOperacion.fromCodigo(
                                rs.getInt("Respuesta")));

                resultado.setMensaje(
                        rs.getString("Mensaje"));
                resultado.setId(rs.getInt("Id"));
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
    public Respuesta<Venta> eliminar(int id) {
        Respuesta<Venta> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Venta_Anular(?)}");

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
    public Respuesta<List<Venta>> listar() {
        Respuesta<List<Venta>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_Venta_Listar()}");

            rs = cs.executeQuery();
            List<Venta> lst = new ArrayList<>();
            while(rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCliente_Id(rs.getInt("Cliente_Id"));
                cliente.setNombre(rs.getString("Nombre"));
                
                TipoDocumentoVenta tdv = new TipoDocumentoVenta();
                tdv.setTipoDocumentoVenta_Id(rs.getInt("TipoDocumentoVenta_Id"));
                tdv.setDocumentoVenta(rs.getString("DocumentoVenta"));
                                
                Venta _venta=new Venta();
                _venta.setVenta_Id(rs.getInt("Venta_Id"));
                _venta.setSerie(rs.getString("Serie"));
                _venta.setCorrelativo(rs.getInt("Correlativo"));
                _venta.setFechaCompleta(rs.getObject("FechaCompleta", LocalDateTime.class));
                _venta.setTipoDocumentoVenta(tdv);
                _venta.setCliente(cliente);
                _venta.setTotal(BigDecimal.valueOf(rs.getDouble("Total")));
                _venta.setEstadoVenta(EstadoVenta.fromId(rs.getInt("Estado")));
                lst.add(_venta);
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
