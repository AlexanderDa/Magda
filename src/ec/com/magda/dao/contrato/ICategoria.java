/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.contrato;

import ec.com.magda.rnegocio.entidades.Categoria;
import java.util.List;

/**
 *
 * @author alexander
 */
public interface ICategoria {

    int insertar(Categoria categoria) throws Exception;

    int modificar(Categoria categoria) throws Exception;

    int eliminar(Categoria categoria) throws Exception;

    Categoria obtener(int id) throws Exception;
    
    Categoria obtener(String descripcion) throws Exception;

    List<Categoria> obtener() throws Exception;

}
