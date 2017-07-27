/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.rnegocio.entidades;

import java.util.Date;

/**
 *
 * @author alexander
 */
public class Factura {

    private int Numero;
    private Empleado Empleado;
    private Cliente Cliente;
    private Date Fecha;
    private double Total;

    public Factura() {
    }

    public Factura(int Numero, Empleado Empleado, Cliente Cliente, Date Fecha, double Total) {
        this.Numero = Numero;
        this.Empleado = Empleado;
        this.Cliente = Cliente;
        this.Fecha = Fecha;
        this.Total = Total;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public Empleado getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(Empleado Empleado) {
        this.Empleado = Empleado;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }
    
    
}
