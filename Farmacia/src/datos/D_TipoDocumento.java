package datos;

import java.util.List;
import modelo.Respuesta;
import modelo.TipoDocumento;
import java.sql.*;
import java.util.ArrayList;
import modelo.EstadoOperacion;

/**
 *
 * @author Migue
 */
public class D_TipoDocumento extends RepositorioBase {
    
    public Respuesta<List<TipoDocumento>> listar() {
        Respuesta<List<TipoDocumento>> resultado = new Respuesta<>();

        Connection cn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {

            cn = getConexion();

            cs = cn.prepareCall("{CALL USP_TipoDocumento_Listar()}");

            rs = cs.executeQuery();
            List<TipoDocumento> lst = new ArrayList<>();
            while(rs.next()) {

                lst.add(new TipoDocumento(
                        rs.getInt("TipoDocumento_Id"),
                        rs.getString("DocumentoDetalle"),
                        rs.getString("Abreviatura"),
                        rs.getInt("Defecto")
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
