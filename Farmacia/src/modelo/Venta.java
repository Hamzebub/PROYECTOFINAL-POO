/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author delac
 */
public class Venta {
    private int venta_Id;
    private String serie;
    private int correlativo;
    private LocalDateTime fechaCompleta;
    private TipoDocumentoVenta tipoDocumentoVenta;
    private BigDecimal total;
    private Cliente cliente;
    private Usuario usuario;
    private EstadoVenta estadoVenta;
    private List<VentaDetalle> ventaDetalle;

    public Venta() {
    }

    public Venta(int venta_Id, String serie, int correlativo, LocalDateTime fechaCompleta, TipoDocumentoVenta tipoDocumentoVenta, BigDecimal total, Cliente cliente, Usuario usuario, EstadoVenta estadoVenta) {
        this.venta_Id = venta_Id;
        this.serie = serie;
        this.correlativo = correlativo;
        this.fechaCompleta = fechaCompleta;
        this.tipoDocumentoVenta = tipoDocumentoVenta;
        this.total = total;
        this.cliente = cliente;
        this.usuario = usuario;
        this.estadoVenta = estadoVenta;
    }

    public Venta(int venta_Id, String serie, int correlativo, LocalDateTime fechaCompleta, TipoDocumentoVenta tipoDocumentoVenta, BigDecimal total, Cliente cliente, Usuario usuario, EstadoVenta estadoVenta, List<VentaDetalle> ventaDetalle) {
        this.venta_Id = venta_Id;
        this.serie = serie;
        this.correlativo = correlativo;
        this.fechaCompleta = fechaCompleta;
        this.tipoDocumentoVenta = tipoDocumentoVenta;
        this.total = total;
        this.cliente = cliente;
        this.usuario = usuario;
        this.estadoVenta = estadoVenta;
        this.ventaDetalle = ventaDetalle;
    }
    
    

    public int getVenta_Id() {
        return venta_Id;
    }

    public void setVenta_Id(int venta_Id) {
        this.venta_Id = venta_Id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public LocalDateTime getFechaCompleta() {
        return fechaCompleta;
    }

    public void setFechaCompleta(LocalDateTime fechaCompleta) {
        this.fechaCompleta = fechaCompleta;
    }

    public TipoDocumentoVenta getTipoDocumentoVenta() {
        return tipoDocumentoVenta;
    }

    public void setTipoDocumentoVenta(TipoDocumentoVenta tipoDocumentoVenta) {
        this.tipoDocumentoVenta = tipoDocumentoVenta;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public List<VentaDetalle> getVentaDetalle() {
        return ventaDetalle;
    }

    public void setVentaDetalle(List<VentaDetalle> ventaDetalle) {
        this.ventaDetalle = ventaDetalle;
    }
    
    
    
    
    
    


    
}
