package logica;

import datos.D_TipoDocumento;
import java.util.List;
import modelo.Respuesta;
import modelo.TipoDocumento;

public class L_TipoDocumento {
    private static L_TipoDocumento obj;
    private List<TipoDocumento> lista;
    
    private L_TipoDocumento(){
    
    }
    
    public static L_TipoDocumento getInstancia() {
        if (obj == null) {
            obj = new L_TipoDocumento();
        }
        return obj;
    }
    
    private void cargarListar(){
        D_TipoDocumento _obj = new D_TipoDocumento();
        Respuesta<List<TipoDocumento>> r = _obj.listar();
        if(r.esCorrecto()){
            lista = r.getDatos();
        }else{
            System.out.println("ERROR: "+ r.getMensaje());
        }
    }
    
    public List<TipoDocumento> obtenerListar(){
        return lista;
    }
    
    public TipoDocumento obtenerDefecto() {

        return lista.stream()
                .filter(t -> t.getDefecto()==1)
                .findFirst()
                .orElse(null);
    }
    
    
    
    
}
