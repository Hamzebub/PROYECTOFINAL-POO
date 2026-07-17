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
    private int puntosAcumulados;
    private int activo;

    public Cliente(int id, TipoDocumento tipoDocumento,int puntosAcumulados,String dni, String nombre, String telefono,int activo) {  
        super(id, dni, nombre, telefono);
        this.tipoDocumento = tipoDocumento;
        this.puntosAcumulados = puntosAcumulados;
        this.activo = activo;
    }
    
    public Cliente(int id,String dni,String nombre,String telefono,int activo) {
        super(id, dni, nombre, telefono);
        this.activo = activo;
    }
    
    public Cliente(int id,TipoDocumento tipoDocumento,String dni,String nombre,String telefono) {
        super(id, dni, nombre, telefono);
        this.tipoDocumento = tipoDocumento;
        this.puntosAcumulados = 0;
        this.activo = 1;
    }
    
    public Cliente() {
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
    public String toString() {
        return super.nombre;
    }
    
    

    @Override
    public void mostrarInfo() {
        System.out.println(nombre);
    }
}
