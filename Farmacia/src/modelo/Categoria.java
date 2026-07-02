package modelo;

public class Categoria {
    private int categoria_Id;
    private String categoria_Detalle;

    public Categoria(int categoria_Id, String categoria_Detalle) {
        this.categoria_Id = categoria_Id;
        this.categoria_Detalle = categoria_Detalle;
    }

    public Categoria() {
    }

    public int getCategoria_Id() {
        return categoria_Id;
    }

    public void setCategoria_Id(int categoria_Id) {
        this.categoria_Id = categoria_Id;
    }

    public String getCategoria_Detalle() {
        return categoria_Detalle;
    }

    public void setCategoria_Detalle(String categoria_Detalle) {
        this.categoria_Detalle = categoria_Detalle;
    }
    
    
    
}
