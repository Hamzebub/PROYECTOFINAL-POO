/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author delac
 */
public class Cliente extends Persona {
    private int Cliente_Id;
    private TipoDocumento tipoDocumento;
    private int puntosAcumulados;
    private int activo;

    public Cliente(int Cliente_Id,TipoDocumento tipoDocumento,int puntosAcumulados,String dni, String nombre, String telefono,int activo) {  
        super(dni, nombre, telefono);
        this.Cliente_Id = Cliente_Id;
        this.tipoDocumento = tipoDocumento;
        this.puntosAcumulados = puntosAcumulados;
        this.activo = activo;
    }
    
    public Cliente(int cliente_Id,String dni,String nombre,String telefono,int activo) {
        super(dni, nombre, telefono);
        this.Cliente_Id = cliente_Id;
        this.activo = activo;
    }
    
    public Cliente(int cliente_Id,TipoDocumento tipoDocumento,String dni,String nombre,String telefono) {
        super(dni, nombre, telefono);
        this.Cliente_Id = cliente_Id;
        this.tipoDocumento = tipoDocumento;
        this.puntosAcumulados = 0;
        this.activo = 1;
    }
    
    public Cliente() {
    }
    
    public int getCliente_Id() {
        return Cliente_Id;
    }

    public void setCliente_Id(int Cliente_Id) {
        this.Cliente_Id = Cliente_Id;
    }
    
    public Cliente getCliente(){
        return this;
    }
    
    public Cliente setCliente(){
        return this;
    }
    
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void sumarPuntos(int puntos) {
        puntosAcumulados += puntos;
    }

    @Override
    public void mostrarInfo() {
        System.out.println(nombre);
    }
}
