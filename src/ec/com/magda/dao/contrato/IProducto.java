/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.contrato;


import ec.com.magda.rnegocio.entidades.Producto;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IProducto {

    int insertar(Producto producto) throws Exception;

    int modificar(Producto producto) throws Exception;

    int eliminar(Producto producto) throws Exception;

    Producto obtener(int id) throws Exception;
    
    Producto obtener(String descripcion) throws Exception;

    List<Producto> obtener() throws Exception;

}
