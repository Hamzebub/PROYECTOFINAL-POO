/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author delac
 */
public class Medicamento extends Producto{
    
    private String laboratorio;
    private String fechaVencimiento;

    public Medicamento() {
    }

    public Medicamento(String codigo, String nombre,
                       double precio, int stock,
                       String laboratorio,
                       String fechaVencimiento) {

        super(codigo, nombre, precio, stock);
        this.laboratorio = laboratorio;
        this.fechaVencimiento = fechaVencimiento;
    }
    
}
