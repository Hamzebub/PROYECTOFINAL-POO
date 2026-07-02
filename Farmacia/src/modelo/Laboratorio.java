package modelo;


public class Laboratorio {
    
    private int laboratorio_Id;
    private String laboratorio_Detalle;

    public Laboratorio() {
    }

    public Laboratorio(int marca_Id, String marca_Detalle) {
        this.laboratorio_Id = marca_Id;
        this.laboratorio_Detalle = marca_Detalle;
    }

    public int getMarca_Id() {
        return laboratorio_Id;
    }

    public void setMarca_Id(int marca_Id) {
        this.laboratorio_Id = marca_Id;
    }

    public String getMarca_Detalle() {
        return laboratorio_Detalle;
    }

    public void setMarca_Detalle(String marca_Detalle) {
        this.laboratorio_Detalle = marca_Detalle;
    }
}
