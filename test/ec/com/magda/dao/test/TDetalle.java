/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.test;

import ec.com.magda.dao.contrato.ICliente;
import ec.com.magda.dao.contrato.IDetalle;
import ec.com.magda.dao.contrato.IEmpleado;
import ec.com.magda.dao.contrato.IFactura;
import ec.com.magda.dao.contrato.IProducto;
import ec.com.magda.dao.impl.impCliente;
import ec.com.magda.dao.impl.impDetalle;
import ec.com.magda.dao.impl.impEmpleado;
import ec.com.magda.dao.impl.impFactura;
import ec.com.magda.dao.impl.impProducto;
import ec.com.magda.rnegocio.entidades.Detalle;
//import ec.com.magda.vistas.Detalle;
//import ec.com.magda.vistas.Producto;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexander
 */
public class TDetalle {

    public TDetalle() {
    }

    @Before
    public void setUp() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {

        IFactura sqlFactura = new impFactura();
        ICliente sqlCliente = new impCliente();
        IEmpleado sqlEmpleado = new impEmpleado();
        IProducto sqlProducto = new impProducto();
        IDetalle sqlDetalle = new impDetalle();
//INSERTAR
        int insertado = 0;
        try {
            Detalle detalle = new Detalle(sqlFactura.obtener(1), sqlProducto.obtener(112), 1);
            insertado = sqlDetalle.insertar(detalle);

        } catch (Exception e) {
        }
        assertTrue(insertado > 0);
//MODIFICAR
        int modificados = 0;
        try {
            Detalle detalle = new Detalle(sqlFactura.obtener(1), sqlProducto.obtener(112), 1000);
            modificados = sqlDetalle.modificar(detalle);

        } catch (Exception e) {
        }
        assertTrue(modificados > 0);
////OBTENER
        Detalle detalle1 = null;
        try {
            detalle1 = sqlDetalle.obtener(1, 1);
            if (detalle1 != null) {
                
                System.out.println("Factura\tproducto\tcantidad\tprecio\ttotal");
                System.out.println("********************************************************");
                System.out.println(
                        detalle1.getFactura().getNumero() + "\t"
                        + detalle1.getProducto().getDescripcion() + "\t"
                        + detalle1.getCantidad() + "\t$"
                        + detalle1.getProducto().getPrecio() + "\t$"
                        + (detalle1.getCantidad() * detalle1.getProducto().getPrecio()));
                System.out.println("********************************************************");
            }
        } catch (Exception e) {
        }
        assertTrue(detalle1 != null);
////LISTAR
        List<Detalle> lista = new ArrayList<>();
        try {
            lista = sqlDetalle.obtener();
            if (lista.size() > 0) {
                for (Detalle detalle : lista) {

                    System.out.println(
                            detalle.getFactura().getNumero() + "\t"
                            + detalle.getProducto().getDescripcion() + "\t"
                            + detalle.getCantidad() + "\t$"
                            + detalle.getProducto().getPrecio() + "\t$"
                            + (detalle.getCantidad() * detalle.getProducto().getPrecio()));
                }
            }
        } catch (Exception e) {
        }

        assertTrue(lista.size() > 0);
//ELIMINAR
        int eliminados = 0;
        try {
            Detalle detalle = new Detalle(sqlFactura.obtener(1), sqlProducto.obtener(112), 1000);
            eliminados = sqlDetalle.eliminar(detalle);

        } catch (Exception e) {
        }
        assertTrue(eliminados > 0);
    }
}
