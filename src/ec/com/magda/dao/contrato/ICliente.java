/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.contrato;


import ec.com.magda.rnegocio.entidades.Cliente;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface ICliente {

    int insertar(Cliente cliente) throws Exception;

    int modificar(Cliente cliente) throws Exception;

    int eliminar(Cliente cliente) throws Exception;

    Cliente obtener(String cedula) throws Exception;

    List<Cliente> obtener() throws Exception;

}
