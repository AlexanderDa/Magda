/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.formularios;

import com.jfoenix.controls.JFXTextField;
import ec.com.magda.dao.contrato.ICliente;
import ec.com.magda.dao.impl.ClienteImp;
import ec.com.magda.rnegocio.entidades.Cliente;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author alexander
 */
public class frmReportes {

    public void formInsertar(AnchorPane root) {
        StackPane fondo = new StackPane();
        {

            VBox Contenedor = new VBox(25);
            {

                AnchorPane contenido = new AnchorPane();
                {
                    //Aqui el contenido de los reportes
                    Button btn = new Button("prueba");
                    contenido.getChildren().addAll(btn/*Aqui los conponentes creados*/);
                    contenido.setStyle("-fx-background-color:red");
                }

                HBox ctnBotones = new HBox(15);
                {
                    Button btnAceptar = new Button("Aceptar");
                    Button btnCancelar = new Button("Cancelar");
                    btnCancelar.setOnAction((t) -> {
                        root.getChildren().remove(fondo);
                    });
                    ctnBotones.getChildren().addAll(btnCancelar, btnAceptar);
                }
                Contenedor.getChildren().addAll(contenido,ctnBotones);

                Contenedor.setPadding(new Insets(15));
                Contenedor.setStyle("-fx-background-color:rgb(245,245,245);-fx-background-radius:10px");
                Contenedor.setMaxSize(600, 470);
            }
            fondo.setStyle("-fx-background-color:rgba(25,25,25,0.6)");
            AnchorPane.setBottomAnchor(fondo, 0.0);
            AnchorPane.setLeftAnchor(fondo, 0.0);
            AnchorPane.setTopAnchor(fondo, 0.0);
            AnchorPane.setRightAnchor(fondo, 0.0);
            fondo.getChildren().add(Contenedor);
        }
        root.getChildren().add(fondo);
    }

}
