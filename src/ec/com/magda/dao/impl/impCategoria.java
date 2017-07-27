/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.impl;

import ec.com.magda.accesodatos.Conexion;
import ec.com.magda.accesodatos.Parametro;
import ec.com.magda.dao.contrato.ICategoria;
import ec.com.magda.rnegocio.entidades.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexander
 */
public class impCategoria implements ICategoria {

    @Override
    public int insertar(Categoria categoria) throws Exception {
        int insertados = 0;
        String sql = "INSERT INTO public.categoria VALUES (?, ?);";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, categoria.getId()));
        prts.add(new Parametro(2, categoria.getDescripcion()));;
        Conexion con = new Conexion();
        try {
            insertados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }
        return insertados;
    }

    @Override
    public int modificar(Categoria categoria) throws Exception {
        int modificado = 0;
        String sql = "UPDATE public.categoria SET descripcion=? WHERE id = ?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, categoria.getDescripcion()));
        prts.add(new Parametro(2, categoria.getId()));
        Conexion con = new Conexion();
        try {
            modificado = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return modificado;
    }

    @Override
    public int eliminar(Categoria categoria) throws Exception {
        int eliminados = 0;
        String sql = "DELETE FROM categoria WHERE id=?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, categoria.getId()));
        Conexion con = new Conexion();
        try {
            eliminados = con.ejecutaComando(sql, prts);
        } catch (Exception e) {
            throw e;
        }

        return eliminados;
    }

    @Override
    public Categoria obtener(int id) throws Exception {
       Categoria categoria= null;
       String sql = "SELECT id, descripcion FROM categoria where id = ?;";
        List<Parametro> prts = new ArrayList<>();
        prts.add(new Parametro(1, id));
        Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql, prts);
            while (rst.next()) {
                categoria = new Categoria();
                categoria.setId(rst.getInt(1));
                categoria.setDescripcion(rst.getString(2));
            }

        } catch (SQLException e) {
            throw e;
        }
       return categoria;
    }

    @Override
    public List<Categoria> obtener() throws Exception {
        List<Categoria> lista = new ArrayList<>();
       String sql ="SELECT id, descripcion FROM categoria";
       Conexion con = new Conexion();
        try {
            ResultSet rst = con.ejecutarQuery(sql);
            while (rst.next()) {                
                Categoria categoria = new Categoria();
                categoria.setId(rst.getInt(1));
                categoria.setDescripcion(rst.getString(2));
                lista.add(categoria);
            }
        } catch (SQLException e) {
            throw e;
        }
       
       return lista;
    }

}
