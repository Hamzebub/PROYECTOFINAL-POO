package datos;

import java.util.List;
import modelo.Respuesta;
import modelo.TipoDocumentoVenta;
import java.sql.*;
import java.util.ArrayList;
import modelo.EstadoOperacion;

/**
 *
 * @author Migue
 */
public class D_TipoDocumentoVenta extends RepositorioBase{
    public Respuesta<List<TipoDocumentoVenta>> listar() {
        Respuesta<List<TipoDocumentoVenta>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_TipoDocumentoVenta_Listar()}");

            rs = cs.executeQuery();
            List<TipoDocumentoVenta> lst = new ArrayList<>();
            while(rs.next()) {

                lst.add(new TipoDocumentoVenta(
                        rs.getInt("TipoDocumentoVenta_Id"),
                        rs.getString("DocumentoVenta"),
                        rs.getString("Indicador")
                        
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
