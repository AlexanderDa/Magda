/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.contrato;



import ec.com.magda.rnegocio.entidades.Factura;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IFactura {

    int insertar(Factura factura) throws Exception;

    int modificar(Factura factura) throws Exception;

    int eliminar(Factura factura) throws Exception;

    Factura obtener(int numero) throws Exception;

    List<Factura> obtener() throws Exception;

}
