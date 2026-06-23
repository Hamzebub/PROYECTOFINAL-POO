/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author delac
 */
public class ProductoFarmacia extends Producto implements Inventariable {

    private String categoria;
    private String marca;

    public ProductoFarmacia() {
    }

    public ProductoFarmacia(String categoria, String marca,String codigo, 
            String nombre, double precio, int stock, String fechaVencimiento) {

        super(codigo, nombre, precio, stock, fechaVencimiento);
        this.categoria = categoria;
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public void mostrarInfo() {
        System.out.println(nombre + " - " + marca);
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
