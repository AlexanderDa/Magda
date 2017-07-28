/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.formularios;

import ec.com.magda.dao.contrato.IFactura;
import ec.com.magda.dao.impl.impFactura;
import ec.com.magda.rnegocio.entidades.Empleado;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author acer1
 */
public class frmPrincipal extends Application {

    private static String cedulaEmpleado;
    static AnchorPane root;
    static BorderPane contenedor;
    static Button btnCliente;
    private static frmCliente cliente;
    private static frmEmpleado empleado;
    private static frmFactura factura;
    private static frmCategoria categoria;
    private static frmProducto producto;

    public void setCedulaEmpleado(String aCedulaEmpleado) {
        cedulaEmpleado = aCedulaEmpleado;
    }

    @Override
    public void start(Stage stage) throws Exception {
        cliente = new frmCliente();
        empleado = new frmEmpleado();
        factura = new frmFactura();
        categoria = new frmCategoria();
        producto = new frmProducto();

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
        Scene scene = new Scene(root, 1000, 650);
        scene.getStylesheets().addAll(this.getClass().getResource("estilos/Principal.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Magda");
        stage.getIcons().add(new Image(getClass().getResource("../imagenes/Icono.png").toExternalForm()));
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
                MenuItem itemCategoria = new MenuItem("Categoria");
                itemCategoria.setOnAction((t) -> {
                    categoria.launchTablas();
                });
                MenuItem itemProducto = new MenuItem("Producto");
                itemProducto.setOnAction((t) -> {
                    producto.launchTablas();
                });
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
            btnProducto.setOnAction(btnProductoActionListener());
            Button btnFactura = new Button("Factúra");
            btnFactura.setOnAction(btnfacturaActionListener());
            Button btnCategoria = new Button("Categoría");
            btnCategoria.setOnAction(btnCategoriaActionListener());
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

    public static EventHandler btnfacturaActionListener() {
        EventHandler handler = (t) -> {
            IFactura sqlFactura = new impFactura();
            factura.formFacturacion(contenedor);
            factura.setEmpleado(cedulaEmpleado);
            try {
                factura.setNumeroFactura(sqlFactura.numero());
            } catch (Exception e) {
            }

            factura.formDatos(root, contenedor);

        };
        return handler;
    }

    public static EventHandler btnCategoriaActionListener() {
        EventHandler handler = (t) -> {
            categoria.formDatos(root, contenedor);
            categoria.formTablas(contenedor);
        };
        return handler;
    }

    public static EventHandler btnProductoActionListener() {
        EventHandler handler = (t) -> {
            producto.formDatos(root, contenedor);
            producto.formTablas(contenedor);
        };
        return handler;
    }

}
