
package util;

import Interfaces.FiltrarItem;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;


public final class cboUtil {
    
    public static <T> void llenarCombo(JComboBox combo, List<T> lista) {
        llenarCombo(combo,lista,null);
    }
    
    public static <T> void llenarCombo(JComboBox combo, List<T> lista, T primerElemento) {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();

        if (primerElemento != null) {
            modelo.addElement(primerElemento);
        }
        lista.forEach(modelo::addElement);
        combo.setModel(modelo);
    }
    
    public static<T> T filtrarItem(List<T> lista, FiltrarItem<T> filtro){
        return lista.stream().filter(filtro::filtrar).findFirst().orElse(null);
    
    } 
    
    public static <T> List<T> filtrar(List<T> lista, FiltrarItem<T> filtro) {
        return lista.stream().filter(filtro::filtrar).toList();
    
    }
    
}
