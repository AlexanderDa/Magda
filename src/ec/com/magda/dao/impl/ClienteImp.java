/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.impl;

import ec.com.magda.accesodatos.Conexion;
import ec.com.magda.accesodatos.Parametro;
import ec.com.magda.dao.contrato.ICliente;
import ec.com.magda.rnegocio.entidades.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public class ClienteImp implements ICliente {

    @Override
    public int insertar(Cliente cliente) throws Exception {
        int insertados = 0;
        String sql = "INSERT INTO public.cliente(cedula, nombres, apellidos, telefono) VALUES (?, ?, ?, ?);";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, cliente.getCedula()));
        prts.add(new Parametro(2, cliente.getNombres()));
        prts.add(new Parametro(3, cliente.getApellidos()));
        prts.add(new Parametro(4, cliente.getTelefono()));
        Conexion con = new Conexion();
        try {
            insertados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return insertados;
    }

    @Override
    public int modificar(Cliente cliente) throws Exception {
        int modificados = 0;
        String sql = "UPDATE cliente SET cedula=?, nombres=?, apellidos=?, telefono=? WHERE cedula=?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, cliente.getCedula()));
        prts.add(new Parametro(2, cliente.getNombres()));
        prts.add(new Parametro(3, cliente.getApellidos()));
        prts.add(new Parametro(4, cliente.getTelefono()));
        prts.add(new Parametro(5, cliente.getCedula()));
        Conexion con = new Conexion();
        try {
            modificados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(Cliente cliente) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM public.cliente WHERE cedula=?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, cliente.getCedula()));
        Conexion con = new Conexion();
        try {
            eliminados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return eliminados;
    }

    @Override
    public Cliente obtener(String cedula) throws Exception {
        Cliente cliente = null;
        String sql = "SELECT id, cedula, nombres, apellidos, telefono FROM public.cliente where cedula = ?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, cedula));
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql, prts);
            while (rst.next()) {
                cliente = new Cliente();
                cliente.setId(rst.getInt(1));
                cliente.setCedula(rst.getString(2));
                cliente.setNombres(rst.getString(3));
                cliente.setApellidos(rst.getString(4));
                cliente.setTelefono(rst.getString(5));
            }

        } catch (SQLException e) {
            throw e;
        }
        return cliente;
    }

    @Override
    public List<Cliente> obtener() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id, cedula, nombres, apellidos, telefono FROM public.cliente;";
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql);
            while (rst.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rst.getInt(1));
                cliente.setCedula(rst.getString(2));
                cliente.setNombres(rst.getString(3));
                cliente.setApellidos(rst.getString(4));
                cliente.setTelefono(rst.getString(5));
                lista.add(cliente);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

}
