package modelo;

/**
 *
 * @author Migue
 */
public enum EstadoVenta {
    ACTIVA(1, "Activa"),
    ANULADA(2, "Anulada");

    private final int id;
    private final String descripcion;

    EstadoVenta(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static EstadoVenta fromId(int id) {
        for (EstadoVenta estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
