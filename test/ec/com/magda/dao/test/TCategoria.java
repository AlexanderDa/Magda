/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.test;

import ec.com.magda.dao.contrato.ICategoria;
import ec.com.magda.dao.impl.impCategoria;
import ec.com.magda.rnegocio.entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexander
 */
public class TCategoria {

    /*
                                                                        APROBADO
     */
    public TCategoria() {
    }

    @Before
    public void setUp() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void test() {
        ICategoria sqlCategoria = new impCategoria();
        Categoria categoria = new Categoria(10000, "DE PRUEBA");
//INSERTAR
        int insertados = 0;
        try {
            insertados = sqlCategoria.insertar(categoria);
        } catch (Exception e) {
        }
        assertTrue(insertados > 0);
//MODIFICAR
        categoria.setDescripcion("DE PRUEBA MODIFICADO");
        int modificados = 0;
        try {
            modificados = sqlCategoria.modificar(categoria);
        } catch (Exception e) {
        }

        assertTrue(modificados > 0);
//OBTENER
        Categoria Categoria1 = null;
        try {
            Categoria1 = sqlCategoria.obtener(10000);
        } catch (Exception e) {
        }
        assertTrue(Categoria1 != null);
//LISTAR
        List<Categoria> lista = new ArrayList<>();
        try {
            lista = sqlCategoria.obtener();
            for (Categoria tmp : lista) {
                System.out.println(tmp.getId() + "   " + tmp.getDescripcion());
            }
        } catch (Exception e) {
        }
        assertTrue(lista.size() > 0);
//ELIMINAR
        int eliminados = 0;
        try {
            eliminados = sqlCategoria.eliminar(categoria);
        } catch (Exception e) {
        }
        assertTrue(eliminados > 0);
    }
}
