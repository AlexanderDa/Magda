/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.test;

import ec.com.magda.dao.contrato.IProducto;
import ec.com.magda.dao.impl.impProducto;
import ec.com.magda.rnegocio.entidades.Categoria;
import ec.com.magda.rnegocio.entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexander
 */
public class Tproducto {
/*
                                                                        APROBADO
*/
    public Tproducto() {
    }

    @Before
    public void setUp() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void test() {
        IProducto sqlproducto = new impProducto();
        Producto producto = new Producto(1000,"Falda", 25, 17,new Categoria(1, ""));

//INSERTAR
        int insertados = 0;
        try {
            insertados = sqlproducto.insertar(producto);
        } catch (Exception e) {
        }
        assertTrue(insertados > 0);
//OBTENER
        Producto producto1 = null;
        try {
            producto1 = sqlproducto.obtener(1000);
        } catch (Exception e) {
        }
        assertTrue(producto1 != null);
//MODIFICAR
        int modificados = 0;
        producto.setDescripcion("Falda larga");
        try {
            modificados =sqlproducto.modificar(producto);
        } catch (Exception e) {
        }
        
        assertTrue(modificados > 0);
//LISTAR
        List<Producto> lista = new ArrayList<>();
        try {
            lista = sqlproducto.obtener();
        } catch (Exception e) {
        }
        assertTrue(lista.size() > 0);
//ELIMINAR
        int eliminados = 0;
        try {
            eliminados = sqlproducto.eliminar(producto);
        } catch (Exception e) {
        }
        assertTrue(eliminados > 0);
    }
}
