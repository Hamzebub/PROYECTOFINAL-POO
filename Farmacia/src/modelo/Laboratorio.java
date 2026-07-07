package modelo;


public class Laboratorio {
    
    private int laboratorio_Id;
    private String laboratorio_Detalle;

    public Laboratorio() {
    }

    public Laboratorio(int laboratorio_Id, String laboratorio_Detalle) {
        this.laboratorio_Id = laboratorio_Id;
        this.laboratorio_Detalle = laboratorio_Detalle;
    }

    public int getLaboratorio_Id() {
        return laboratorio_Id;
    }

    public void setLaboratorio_Id(int marca_Id) {
        this.laboratorio_Id = marca_Id;
    }

    public String getLaboratorio_Detalle() {
        return laboratorio_Detalle;
    }

    public void setLaboratorio_Detalle(String marca_Detalle) {
        this.laboratorio_Detalle = marca_Detalle;
    }
    
    @Override
    public String toString() {
        return laboratorio_Detalle;
    }
}
