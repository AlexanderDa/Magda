/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.impl;

import ec.com.magda.accesodatos.Conexion;
import ec.com.magda.accesodatos.Parametro;
import ec.com.magda.dao.contrato.IEmpleado;
import ec.com.magda.rnegocio.entidades.Empleado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public class impEmpleado implements IEmpleado{
    
    @Override
    public int insertar(Empleado empleado) throws Exception {
        int insertados = 0;
        String sql = "INSERT INTO public.empleado(cedula, nombres, apellidos, telefono, direccion, sueldo) VALUES (?, ?, ?, ?, ?, ?);";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, empleado.getCedula()));
        prts.add(new Parametro(2, empleado.getNombres()));
        prts.add(new Parametro(3, empleado.getApellidos()));
        prts.add(new Parametro(4, empleado.getTelefono()));
        prts.add(new Parametro(5, empleado.getDireccion()));
        prts.add(new Parametro(6, empleado.getSueldo()));
        Conexion con = new Conexion();
        try {
            insertados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return insertados;
    }

    @Override
    public int modificar(Empleado empleado) throws Exception {
        int modificados = 0;
        String sql = "UPDATE empleado SET cedula=?, nombres=?, apellidos=?, telefono=?, direccion=?, sueldo=? WHERE cedula=?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, empleado.getCedula()));
        prts.add(new Parametro(2, empleado.getNombres()));
        prts.add(new Parametro(3, empleado.getApellidos()));
        prts.add(new Parametro(4, empleado.getTelefono()));
        prts.add(new Parametro(5, empleado.getDireccion()));
        prts.add(new Parametro(6, empleado.getSueldo()));
        prts.add(new Parametro(7, empleado.getCedula()));
        Conexion con = new Conexion();
        try {
            modificados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return modificados;
    }

    @Override
    public int eliminar(Empleado empleado) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM public.empleado WHERE cedula=?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, empleado.getCedula()));
        Conexion con = new Conexion();
        try {
            eliminados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return eliminados;
    }

    @Override
    public Empleado obtener(String cedula) throws Exception {
        Empleado empleado = null;
        String sql = "SELECT id, cedula, nombres, apellidos, telefono, direccion, sueldo FROM public.empleado where cedula = ?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, cedula));
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql, prts);
            while (rst.next()) {
                empleado = new Empleado();
                empleado.setId(rst.getInt(1));
                empleado.setCedula(rst.getString(2));
                empleado.setNombres(rst.getString(3));
                empleado.setApellidos(rst.getString(4));
                empleado.setTelefono(rst.getString(5));
                empleado.setDireccion(rst.getString(6));
                empleado.setSueldo(rst.getDouble(7));
            }

        } catch (SQLException e) {
            throw e;
        }
        return empleado;
    }

    @Override
    public List<Empleado> obtener() throws Exception {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT id, cedula, nombres, apellidos, telefono, direccion, sueldo FROM public.empleado;";
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql);
            while (rst.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rst.getInt(1));
                empleado.setCedula(rst.getString(2));
                empleado.setNombres(rst.getString(3));
                empleado.setApellidos(rst.getString(4));
                empleado.setTelefono(rst.getString(5));
                empleado.setDireccion(rst.getString(6));
                empleado.setSueldo(rst.getDouble(7));
                lista.add(empleado);
            }

        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

}
