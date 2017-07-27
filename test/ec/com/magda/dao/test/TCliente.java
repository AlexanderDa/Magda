/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.test;

import ec.com.magda.dao.contrato.ICliente;
import ec.com.magda.dao.impl.impCliente;
import ec.com.magda.rnegocio.entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexander
 */
public class TCliente {
/*
                                                                        APROBADO
*/
    public TCliente() {
    }

    @Before
    public void setUp() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void test() {
        ICliente sqlCliente = new impCliente();
        Cliente cliente = new Cliente("094783465-4", "Pedro", "Alcocer", "0934528743");

//INSERTAR       
        int insertados = 0;
        try {
            insertados = sqlCliente.insertar(cliente);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        assertTrue(insertados > 0);
//MODIFICAR
        int modificados = 0;
        cliente.setApellidos("Miranda");
        try {
            modificados = sqlCliente.modificar(cliente);
        } catch (Exception e) {
        }
        assertTrue(modificados > 0);
//OBTENER
        Cliente cliente1 = null;
        try {
            cliente1 = sqlCliente.obtener("094783465-4");
        } catch (Exception e) {
        }
        assertTrue(cliente1 != null);
//LISTAR
        List<Cliente> lista = new ArrayList<>();
        try {
            lista = sqlCliente.obtener();
        } catch (Exception e) {
        }
        assertTrue(lista.size() > 0);
//ELIMINAR
        int eliminados = 0;
        try {
            eliminados = sqlCliente.eliminar(cliente1);
        } catch (Exception e) {
        }
        assertTrue(eliminados > 0);
    }
}
