/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author delac
 */
public class Cliente {
    
    private String dni;
    private String nombre;
    private String telefono;

    public Cliente() {
    }

    public Cliente(String dni, String nombre,
                   String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
}
