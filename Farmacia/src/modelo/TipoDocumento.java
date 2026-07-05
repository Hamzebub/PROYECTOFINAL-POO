package modelo;

/**
 *
 * @author Migue
 */
public class TipoDocumento {
    private int tipoDocumento_Id;
    private String documentoDetalle;
    private String abreviatura;
    private int defecto;

    public TipoDocumento() {
    }

    public TipoDocumento(int tipoDocumento_Id, String documentoDetalle, String abreviatura, int defecto) {
        this.tipoDocumento_Id = tipoDocumento_Id;
        this.documentoDetalle = documentoDetalle;
        this.abreviatura = abreviatura;
        this.defecto = defecto;
    }

    public int getTipoDocumento_Id() {
        return tipoDocumento_Id;
    }

    public void setTipoDocumento_Id(int tipoDocumento_Id) {
        this.tipoDocumento_Id = tipoDocumento_Id;
    }

    public String getDocumentoDetalle() {
        return documentoDetalle;
    }

    public void setDocumentoDetalle(String documentoDetalle) {
        this.documentoDetalle = documentoDetalle;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public int getDefecto() {
        return defecto;
    }

    public void setDefecto(int defecto) {
        this.defecto = defecto;
    }
    
    @Override
    public String toString() {
        return abreviatura; // importante para ComboBox
    }
    
    
    
    
}
