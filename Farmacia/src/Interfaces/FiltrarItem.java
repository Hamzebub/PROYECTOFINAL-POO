package Interfaces;

/**
 *
 * @author Migue
 * @param <T>
 */
@FunctionalInterface
public interface FiltrarItem<T> {
    boolean filtrar(T producto);
}
