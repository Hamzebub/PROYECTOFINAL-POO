package modelo;


public class Marca {
    private int marca_Id;
    private String marca_Detalle;

    public Marca() {
    }

    public Marca(int marca_Id, String marca_Detalle) {
        this.marca_Id = marca_Id;
        this.marca_Detalle = marca_Detalle;
    }

    public int getMarca_Id() {
        return marca_Id;
    }

    public void setMarca_Id(int marca_Id) {
        this.marca_Id = marca_Id;
    }

    public String getMarca_Detalle() {
        return marca_Detalle;
    }

    public void setMarca_Detalle(String marca_Detalle) {
        this.marca_Detalle = marca_Detalle;
    }
    
    
    
}
