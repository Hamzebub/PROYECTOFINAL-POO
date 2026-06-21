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

    public Cliente() {
    }

    public Cliente(int puntosAcumulados,
            String dni, String nombre, String telefono) {

        super(dni, nombre, telefono);
        this.puntosAcumulados = puntosAcumulados;
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
