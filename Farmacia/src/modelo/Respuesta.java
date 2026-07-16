package modelo;

public class Respuesta<T> {
    private EstadoOperacion estado;
    private String mensaje;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private T datos;
    
    public Respuesta() {
    }

    public Respuesta(EstadoOperacion estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public Respuesta(EstadoOperacion estado, String mensaje, T datos) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public Respuesta(EstadoOperacion estado, String mensaje, int id, T datos) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.id = id;
        this.datos = datos;
    }
    
    

    public EstadoOperacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoOperacion estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }
    
    public boolean esCorrecto(){
        return estado== EstadoOperacion.EXITO;
    }
    
    

}
