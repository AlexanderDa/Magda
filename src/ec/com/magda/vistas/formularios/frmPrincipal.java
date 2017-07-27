/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.formularios;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author alexander
 */
public class frmPrincipal extends Application {

    static frmCliente cliente = new frmCliente();
    static frmEmpleado empleado = new frmEmpleado();
    static AnchorPane root;
    static BorderPane contenedor;
    static Button btnCliente;

    @Override
    public void start(Stage stage) throws Exception {
        contenedor = new BorderPane();
        {
            contenedor.setTop(menuBar());
            contenedor.setLeft(panelIzquierdo());
            AnchorPane.setTopAnchor(contenedor, 0.0);
            AnchorPane.setRightAnchor(contenedor, 0.0);
            AnchorPane.setBottomAnchor(contenedor, 0.0);
            AnchorPane.setLeftAnchor(contenedor, 0.0);

        }

        root = new AnchorPane();
        root.getChildren().add(contenedor);
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("estilos/Principal.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Magda");
        //stage.getIcons().add(new Image(getClass().getResource("../imagenes/Icono.png").toExternalForm()));
        stage.show();
    }

    public MenuBar menuBar() {

        MenuBar MBar = new MenuBar();
        {
//            Menu menuLogo = new Menu();
//            {
//                ImageView icono = new ImageView(new Image(getClass().getResourceAsStream("../imagenes/Icono.png")));
//                icono.setFitHeight(20);
//                icono.setFitWidth(20);
//                menuLogo.setGraphic(icono);
//
//            }

            Menu menuArchivo = new Menu("Archivo");
            {
                MenuItem itemEmpleado = new MenuItem("Empleado\t\t");
                itemEmpleado.setOnAction((t) -> {
                    empleado.launchTablas();
                });
                MenuItem itemCliente = new MenuItem("Cliente");
                itemCliente.setOnAction((t) -> {
                    cliente.launchTablas();
                });
                MenuItem itemProducto = new MenuItem("Producto");
                MenuItem itemCategoria = new MenuItem("Categoria");
                MenuItem itemFactura = new MenuItem("Factúra");
                menuArchivo.getItems().addAll(itemCategoria, itemCliente, itemEmpleado, itemFactura, itemProducto);
            }
            MBar.getMenus().addAll(menuArchivo);
        }

        return MBar;
    }

    private static VBox panelIzquierdo() {
        VBox panel = new VBox(15);

        {
            btnCliente = new Button("Clientes");
            btnCliente.setOnAction(btnClienteActionListener());
            Button btnEmpleado = new Button("Empleados");
            btnEmpleado.setOnAction(btnEmpleadoActionListener());
            Button btnProducto = new Button("Productos");
            Button btnFactura = new Button("Factúra");
            Button btnCategoria = new Button("Categoria");
            panel.getStyleClass().add("panel_izquierdo");
            panel.getChildren().addAll(btnCategoria, btnCliente, btnEmpleado, btnFactura, btnProducto);
        }
        return panel;
    }

    /**
     * *************************************************************************
     *                                                                         *
     * IMPLEMENTACION DE LOS EVETOS * *
     * *************************************************************************
     */
    public static EventHandler btnClienteActionListener() {
        EventHandler handler = (t) -> {
            cliente.formDatos(root, contenedor);
            cliente.formTablas(contenedor);
        };
        return handler;
    }

    public static EventHandler btnEmpleadoActionListener() {
        EventHandler handler = (t) -> {
            empleado.formDatos(root, contenedor);
            empleado.formTablas(contenedor);
        };
        return handler;
    }
}
