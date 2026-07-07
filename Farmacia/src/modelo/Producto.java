package modelo;

import java.time.LocalDate;

/**
 *
 * @author delac
 */
public class Producto {
    
    private int producto_Id;
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    private LocalDate fechaVencimiento;
    private Laboratorio laboratorio;
    private Marca marca;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(int producto_Id, String codigo, String nombre, double precio, int stock, LocalDate fechaVencimiento, Laboratorio laboratorio, Marca marca, Categoria categoria) {
        this.producto_Id = producto_Id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.fechaVencimiento = fechaVencimiento;
        this.laboratorio = laboratorio;
        this.marca = marca;
        this.categoria = categoria;
    }

    public int getProducto_Id() {
        return producto_Id;
    }

    public void setProducto_Id(int producto_Id) {
        this.producto_Id = producto_Id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    
    
    
    

    
}
