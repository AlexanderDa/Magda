/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.impl;

import ec.com.magda.accesodatos.Conexion;
import ec.com.magda.accesodatos.Parametro;
import ec.com.magda.dao.contrato.IDetalle;
import ec.com.magda.dao.contrato.IFactura;
import ec.com.magda.dao.contrato.IProducto;
import ec.com.magda.rnegocio.entidades.Detalle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public class impDetalle implements IDetalle {

    @Override
    public int insertar(Detalle detalle) throws Exception {
        int insertados = 0;
        String sql = "INSERT INTO public.detalle(factura, producto, cantidad)  VALUES (?, ?, ?);";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, detalle.getFactura().getNumero()));
        prts.add(new Parametro(2, detalle.getProducto().getId()));
        prts.add(new Parametro(3, detalle.getCantidad()));
        Conexion con = new Conexion();
        try {
            insertados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return insertados;
    }

    @Override
    public int modificar(Detalle detalle) throws Exception {
        int modificado = 0;
        String sql = "UPDATE detalle set cantidad = ? where factura =? and producto = ?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, detalle.getCantidad()));
        prts.add(new Parametro(2, detalle.getFactura().getNumero()));
        prts.add(new Parametro(3, detalle.getProducto().getId()));
        Conexion con = new Conexion();
        try {
            modificado = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return modificado;
    }

    @Override
    public int eliminar(Detalle detalle) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM detalle WHERE factura =? and producto = ?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, detalle.getFactura().getNumero()));
        prts.add(new Parametro(2, detalle.getProducto().getId()));
        Conexion con = new Conexion();
        try {
            eliminados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public Detalle obtener(int factura, int producto) throws Exception {
        Detalle detalle = null;
        IFactura sqlFactura = new impFactura();
        IProducto sqlProducto = new impProducto();
        String sql = "SELECT factura, producto, cantidad  FROM public.detalle WHERE factura =? and producto = ?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, factura));
        prts.add(new Parametro(2, producto));
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql, prts);
            while (rst.next()) {
                detalle = new Detalle();
                detalle.setFactura(sqlFactura.obtener(rst.getInt(1)));
                detalle.setProducto(sqlProducto.obtener(rst.getInt(2)));
                detalle.setCantidad(rst.getInt(3));
            }

        } catch (SQLException e) {
            throw e;
        }
        return detalle;
    }

    @Override
    public List<Detalle> obtener() throws Exception {
        List<Detalle> lista = new ArrayList<>();
        IFactura sqlFactura = new impFactura();
        IProducto sqlProducto = new impProducto();
        String sql = "SELECT factura, producto, cantidad  FROM public.detalle;";
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql);
            while (rst.next()) {
                Detalle detalle = new Detalle();
                detalle.setFactura(sqlFactura.obtener(rst.getInt(1)));
                detalle.setProducto(sqlProducto.obtener(rst.getInt(2)));
                detalle.setCantidad(rst.getInt(3));
                lista.add(detalle);
            }
        } catch (SQLException e) {
            throw e;
        }

        return lista;
    }

}
