/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.impl;

import ec.com.magda.accesodatos.Conexion;
import ec.com.magda.accesodatos.Parametro;
import ec.com.magda.dao.contrato.ICategoria;
import ec.com.magda.dao.contrato.IProducto;
import ec.com.magda.rnegocio.entidades.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public class impProducto implements IProducto {

    @Override
    public int insertar(Producto producto) throws Exception {
        int insertados = 0;
        String sql = "INSERT INTO public.producto VALUES (?, ?, ?, ?, ?);";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, producto.getId()));
        prts.add(new Parametro(2, producto.getDescripcion()));
        prts.add(new Parametro(3, producto.getStock()));
        prts.add(new Parametro(4, producto.getPrecio()));
        prts.add(new Parametro(5, producto.getCategoria().getId()));
        Conexion con = new Conexion();
        try {
            insertados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return insertados;
    }

    @Override
    public int modificar(Producto producto) throws Exception {
        int modificado = 0;
        String sql = "UPDATE public.producto SET descripcion=?, stock=?, precio=?, categoria=?  WHERE id = ?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, producto.getDescripcion()));
        prts.add(new Parametro(2, producto.getStock()));
        prts.add(new Parametro(3, producto.getPrecio()));
        prts.add(new Parametro(4, producto.getCategoria().getId()));
        prts.add(new Parametro(5, producto.getId()));
        Conexion con = new Conexion();
        try {
            modificado = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return modificado;
    }

    @Override
    public int eliminar(Producto producto) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM producto WHERE id=?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, producto.getId()));
        Conexion con = new Conexion();
        try {
            eliminados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public Producto obtener(int id) throws Exception {
        Producto producto = null;
        ICategoria sqlCategoria = new impCategoria();
        String sql = "SELECT id, descripcion, stock, precio, categoria FROM producto where id = ?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, id));
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql, prts);
            while (rst.next()) {
                producto = new Producto();
                producto.setId(rst.getInt(1));
                producto.setDescripcion(rst.getString(2));
                producto.setStock(rst.getInt(3));
                producto.setPrecio(rst.getDouble(4));
                producto.setCategoria(sqlCategoria.obtener(rst.getInt(5)));
            }

        } catch (SQLException e) {
            throw e;
        }
        return producto;
    }

    @Override
    public List<Producto> obtener() throws Exception {
        ICategoria sqlCategoria = new impCategoria();
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id, descripcion, stock, precio, categoria FROM producto";
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql);
            while (rst.next()) {
                Producto producto = new Producto();
                producto.setId(rst.getInt(1));
                producto.setDescripcion(rst.getString(2));
                producto.setStock(rst.getInt(3));
                producto.setPrecio(rst.getDouble(4));
                producto.setCategoria(sqlCategoria.obtener(rst.getInt(5)));
                lista.add(producto);
            }
        } catch (SQLException e) {
            throw e;
        }

        return lista;
    }

}
