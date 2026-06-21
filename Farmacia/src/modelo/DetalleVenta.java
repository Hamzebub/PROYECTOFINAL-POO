/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author delac
 */
public class DetalleVenta {
    
    private int cantidad;
    private double subtotal;
    private double precioUnitario;
    private Producto producto;

    public DetalleVenta() {
    }

    public DetalleVenta(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.precioUnitario = producto.getPrecio();
        calcularSubtotal();
    }

    public void calcularSubtotal() {
        subtotal = cantidad * precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }
    
}
