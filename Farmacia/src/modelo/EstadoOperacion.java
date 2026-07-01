package modelo;


public enum EstadoOperacion {
    ERROR(0),EXITO(1);
    
    private final int codigo;

    EstadoOperacion(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public static EstadoOperacion fromCodigo(int codigo) {

        for (EstadoOperacion estado : values()) {
            if (estado.codigo == codigo) {
                return estado;
            }
        }

        throw new IllegalArgumentException("Código no válido: " + codigo);
    }
    
}
