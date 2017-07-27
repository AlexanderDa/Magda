/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.test;

import ec.com.magda.dao.contrato.ICliente;
import ec.com.magda.dao.contrato.IEmpleado;
import ec.com.magda.dao.contrato.IFactura;
import ec.com.magda.dao.impl.impCliente;
import ec.com.magda.dao.impl.impEmpleado;
import ec.com.magda.dao.impl.impFactura;
import ec.com.magda.rnegocio.entidades.Factura;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexander
 */
public class TFactura {
/*
                                                                        APROBADO
*/
    public TFactura() {
    }

    @Before
    public void setUp() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void test() {
        IFactura sqlFactura = new  impFactura();
        ICliente sqlCliente = new impCliente();
        IEmpleado sqlEmpleado = new impEmpleado();
        Factura factura = null;
//OBTEBER EMPLEADO Y CLIENTE Y ASIGNAR A LA FACTURA
        try {
            factura = new Factura(10000, sqlEmpleado.obtener("048572097-6"), sqlCliente.obtener("060405974-1"), new Date(), 10);
        } catch (Exception e) {
        }

////INSERTAR
        int insertados = 0;
        try {
            insertados = sqlFactura.insertar(factura);
        } catch (Exception e) {
        }
        assertTrue(insertados > 0);
////MODIFICAR
        int modificados = 0;
        try {
            
            modificados = sqlFactura.modificar(factura);
        } catch (Exception e) {
        }
        assertTrue(modificados > 0);
//OBTENER
        Factura factura1 = null;
        try {
            factura1 = sqlFactura.obtener(10000);
        } catch (Exception e) {
        }
        assertTrue(factura1!= null);
////LISTAR
        List<Factura> lista = new ArrayList<>();
        try {
            lista = sqlFactura.obtener();
        } catch (Exception e) {
        }
        assertTrue(lista.size() > 0);
////ELIMINAR
        int eliminados = 0;
        try {
            eliminados = sqlFactura.eliminar(factura);
        } catch (Exception e) {
        }
        assertTrue(eliminados > 0);
    }
}
