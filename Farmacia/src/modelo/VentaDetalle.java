package modelo;

import java.math.BigDecimal;

/**
 *
 * @author delac
 */
public class VentaDetalle {
    private int ventaDetalle_Id;
    private int venta_Id;
    private Producto producto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    public VentaDetalle() {
    }

    public VentaDetalle(int ventaDetalle_Id, int venta_Id, Producto producto, int cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        this.ventaDetalle_Id = ventaDetalle_Id;
        this.venta_Id = venta_Id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getVentaDetalle_Id() {
        return ventaDetalle_Id;
    }

    public void setVentaDetalle_Id(int ventaDetalle_Id) {
        this.ventaDetalle_Id = ventaDetalle_Id;
    }

    public int getVenta_Id() {
        return venta_Id;
    }

    public void setVenta_Id(int venta_Id) {
        this.venta_Id = venta_Id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    
    

    
}
