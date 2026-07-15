package logica;

import datos.D_TipoDocumentoVenta;
import java.util.List;
import modelo.Respuesta;
import modelo.TipoDocumentoVenta;

/**
 *
 * @author Migue
 */
public class L_TipoDocumentoVenta {
    private static L_TipoDocumentoVenta obj;
    private List<TipoDocumentoVenta> lista;
    
    public L_TipoDocumentoVenta(){
    cargarListar();
    }
    
    public static L_TipoDocumentoVenta getInstancia() {
        if (obj == null) {
            obj = new L_TipoDocumentoVenta();
        }
        return obj;
    }
    
    private void cargarListar(){
        D_TipoDocumentoVenta _obj = new D_TipoDocumentoVenta();
        Respuesta<List<TipoDocumentoVenta>> r = _obj.listar();
        if(r.esCorrecto()){
            lista = r.getDatos();
        }else{
            System.out.println("ERROR: "+ r.getMensaje());
        }
    }
    
    public List<TipoDocumentoVenta> obtenerListar(){
        return lista;
    }
    
}
