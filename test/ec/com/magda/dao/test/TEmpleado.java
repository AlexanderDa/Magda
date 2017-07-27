/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.test;

import ec.com.magda.dao.contrato.IEmpleado;
import ec.com.magda.dao.impl.impEmpleado;
import ec.com.magda.rnegocio.entidades.Empleado;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexander
 */
public class TEmpleado {
/*
                                                                        APROBADO
*/
    public TEmpleado() {
    }

    @Before
    public void setUp() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void test() {
        IEmpleado sqlEmpleado = new impEmpleado();
        Empleado empleado = new Empleado("111111111-1", "a", "b", "c", "d", 500.5);
//INSERTAR       
        int insertados = 0;
        try {
            insertados = sqlEmpleado.insertar(empleado);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assertTrue(insertados > 0);
//MODIFICAR
        int modificados = 0;
        empleado.setApellidos("Miranda");
        try {
            modificados = sqlEmpleado.modificar(empleado);
        } catch (Exception e) {
        }
        assertTrue(modificados > 0);

//OBTENER
        Empleado empleado1 = null;
        try {
            empleado1 = sqlEmpleado.obtener("111111111-1");
        } catch (Exception e) {
        }
        assertTrue(empleado1 != null);
//LISTAR
        List<Empleado> lista = new ArrayList<>();
        try {
            lista = sqlEmpleado.obtener();
        } catch (Exception e) {
        }
        assertTrue(lista.size() > 0);
//ELIMINAR
        int eliminados = 0;
        try {
            eliminados = sqlEmpleado.eliminar(empleado1);
        } catch (Exception e) {
        }
        assertTrue(eliminados > 0);

    }
}
