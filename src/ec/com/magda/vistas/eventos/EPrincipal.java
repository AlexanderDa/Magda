/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.eventos;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author alexander
 */
public class EPrincipal {

    public EventHandler mostrarCliente(BorderPane contenedor) {
        EventHandler handler = (t) -> {
            System.out.println("Hola mundo");
        };
        return handler;
    }
}
