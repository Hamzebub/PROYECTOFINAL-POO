/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author delac
 */
public class Medicamento extends Producto implements Inventariable {

    private String laboratorio;
    private String fechaVencimiento;

    public Medicamento() {
    }

    public Medicamento(String laboratorio, String fechaVencimiento,
            String codigo, String nombre, double precio, int stock) {

        super(codigo, nombre, precio, stock);
        this.laboratorio = laboratorio;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public void mostrarInfo() {
        System.out.println(nombre + " - " + laboratorio);
    }

    @Override
    public void actualizarStock(int cantidad) {
        stock += cantidad;
    }

    @Override
    public int obtenerStock() {
        return stock;
    }
}
