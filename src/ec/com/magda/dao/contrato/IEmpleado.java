/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.contrato;


import ec.com.magda.rnegocio.entidades.Empleado;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IEmpleado {

    int insertar(Empleado empleado) throws Exception;

    int modificar(Empleado empleado) throws Exception;

    int eliminar(Empleado empleado) throws Exception;

    Empleado obtener(String cedula) throws Exception;

    List<Empleado> obtener() throws Exception;

}
