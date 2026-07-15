package modelo;

/**
 *
 * @author Migue
 */
public class TipoDocumentoVenta {
    private int tipoDocumentoVenta_Id;
    private String documentoVenta;
    private String indicador;

    public TipoDocumentoVenta(int tipoDocumentoVenta_Id, String documentoVenta, String indicador) {
        this.tipoDocumentoVenta_Id = tipoDocumentoVenta_Id;
        this.documentoVenta = documentoVenta;
        this.indicador = indicador;
    }

    public TipoDocumentoVenta() {
    }

    public int getTipoDocumentoVenta_Id() {
        return tipoDocumentoVenta_Id;
    }

    public void setTipoDocumentoVenta_Id(int tipoDocumentoVenta_Id) {
        this.tipoDocumentoVenta_Id = tipoDocumentoVenta_Id;
    }

    public String getDocumentoVenta() {
        return documentoVenta;
    }

    public void setDocumentoVenta(String documentoVenta) {
        this.documentoVenta = documentoVenta;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    @Override
    public String toString() {
        return documentoVenta;
    }
    
    
    
    
    
}
