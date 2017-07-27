/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.dao.contrato;


import ec.com.magda.rnegocio.entidades.Detalle;
import java.util.List;

/**
 *
 * @author usuario
 */
public interface IDetalle {

    int insertar(Detalle detalle) throws Exception;

    int modificar(Detalle detalle) throws Exception;

    int eliminar(Detalle detalle) throws Exception;

    Detalle obtener(int factura,int producto) throws Exception;

    List<Detalle> obtener() throws Exception;

}
