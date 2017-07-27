/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.impl;

import ec.com.magda.accesodatos.Conexion;
import ec.com.magda.accesodatos.Parametro;
import ec.com.magda.dao.contrato.ICliente;
import ec.com.magda.dao.contrato.IEmpleado;
import ec.com.magda.dao.contrato.IFactura;
import ec.com.magda.dao.contrato.IFactura;
import ec.com.magda.rnegocio.entidades.Factura;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public class impFactura implements IFactura {

    @Override
    public int insertar(Factura factura) throws Exception {
        int insertados = 0;
        String sql = "INSERT INTO public.factura(numero, cliente, empleado, fecha,total)  VALUES (?,?, ?, ?,0);";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, factura.getNumero()));
        prts.add(new Parametro(2, factura.getCliente().getId()));
        prts.add(new Parametro(3, factura.getEmpleado().getId()));
        prts.add(new Parametro(4, factura.getFecha())); 
        Conexion con = new Conexion();
        try {
            insertados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return insertados;
    }

    @Override
    public int modificar(Factura factura) throws Exception {
        int modificados = 0;
        String sql = "UPDATE public.factura SET  cliente=?, empleado=?, fecha=? WHERE numero=?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, factura.getCliente().getId()));
        prts.add(new Parametro(2, factura.getEmpleado().getId()));
        prts.add(new Parametro(3, factura.getFecha()));
        prts.add(new Parametro(4, factura.getNumero()));
        Conexion con = new Conexion();
        try {
            modificados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(Factura factura) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM public.factura WHERE numero=?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, factura.getNumero()));
        Conexion con = new Conexion();
        try {
            eliminados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return eliminados;
    }

    @Override
    public Factura obtener(int numero) throws Exception {
        Factura factura = null;
        IEmpleado sqlEmpleado = new impEmpleado();
        ICliente sqlCliente = new impCliente();
        String sql = "SELECT numero, cliente, empleado, fecha, total  FROM public.factura WHERE numero =?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, numero));
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql, prts);
            while (rst.next()) {
                factura = new Factura();
                factura.setNumero(rst.getInt(1));
                factura.setCliente(sqlCliente.obtener(rst.getString(2)));
                factura.setEmpleado(sqlEmpleado.obtener(rst.getString(3)));
                factura.setFecha(rst.getDate(4));
                factura.setTotal(rst.getDouble(5));
            }

        } catch (SQLException e) {
            throw e;
        }
        return factura;
    }

    @Override
    public List<Factura> obtener() throws Exception {
        List<Factura> lista = new ArrayList<>();
        IEmpleado sqlEmpleado = new impEmpleado();
        ICliente sqlCliente = new impCliente();
        String sql = "SELECT numero, cliente, empleado, fecha, total  FROM public.factura;";
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql);
            while (rst.next()) {
                Factura factura = new Factura();
                factura.setNumero(rst.getInt(1));
                factura.setCliente(sqlCliente.obtener(rst.getString(2)));
                factura.setEmpleado(sqlEmpleado.obtener(rst.getString(3)));
                factura.setFecha(rst.getDate(4));
                factura.setTotal(rst.getDouble(5));
                lista.add(factura);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

}
