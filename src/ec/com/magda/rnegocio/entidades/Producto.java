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
public class Producto {

    private int Id;
    private String Descripcion;
    private int Stock;
    private double Precio;
    private Categoria Categoria;

    public Producto() {
    }

    public Producto(int Id,String Descripcion, int Stock, double Precio,Categoria categoria) {
        this.Id = Id;
        this.Descripcion = Descripcion;
        this.Stock = Stock;
        this.Precio = Precio;
        this.Categoria = categoria;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public Categoria getCategoria() {
        return Categoria;
    }

    public void setCategoria(Categoria Categoria) {
        this.Categoria = Categoria;
    }

}
