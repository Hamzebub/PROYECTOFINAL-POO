/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author delac
 */
public class Venta {
    
    private String numeroVenta;
    private String fecha;
    private double total;

    private Cliente cliente;
    private Usuario usuario;

    private ArrayList<DetalleVenta> detalles;

    public Venta() {
        detalles = new ArrayList<>();
    }

    public Venta(String numeroVenta,
                 String fecha,
                 Cliente cliente,
                 Usuario usuario) {

        this.numeroVenta = numeroVenta;
        this.fecha = fecha;
        this.cliente = cliente;
        this.usuario = usuario;

        detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
    }

    public void calcularTotal() {

        total = 0;

        for (DetalleVenta d : detalles) {
            total += d.getSubtotal();
        }
    }

    public void registrarVenta() {
        calcularTotal();
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }
    
}
