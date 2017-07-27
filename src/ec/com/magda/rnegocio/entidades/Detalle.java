/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.rnegocio.entidades;

/**
 *
 * @author alexander
 */
public class Detalle {

    private Factura Factura;
    private Producto Producto;
    private int Cantidad;

    public Detalle() {
    }

    public Detalle(Factura Factura, Producto Producto, int Cantidad) {
        this.Factura = Factura;
        this.Producto = Producto;
        this.Cantidad = Cantidad;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Factura getFactura() {
        return Factura;
    }

    public void setFactura(Factura Factura) {
        this.Factura = Factura;
    }

    public Producto getProducto() {
        return Producto;
    }

    public void setProducto(Producto Producto) {
        this.Producto = Producto;
    }
    
}
