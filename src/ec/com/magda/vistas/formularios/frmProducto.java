/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.formularios;

import com.jfoenix.controls.JFXTextField;
import ec.com.magda.dao.contrato.ICategoria;
import ec.com.magda.dao.contrato.IProducto;
import ec.com.magda.dao.impl.CategoriaImp;
import ec.com.magda.dao.impl.ProductoImp;
import ec.com.magda.rnegocio.entidades.Producto;
import java.util.Date;
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
 * @author acer1
 */
public class frmProducto {

    private JFXTextField tfDescripcion;
    private JFXTextField tfStock;
    private JFXTextField tfPrecio;
    private JFXTextField tfCategoria;
    private JFXTextField tfIDescripcion;
    private JFXTextField tfIStock;
    private JFXTextField tfIPrecio;
    private JFXTextField tfICategoria;

    public void formInsertar(AnchorPane root) {
        StackPane fondo = new StackPane();
        {

            VBox Contenedor = new VBox(25);
            {
                tfIDescripcion = new JFXTextField();
                tfIDescripcion.setPromptText("Descripción");
                tfIDescripcion.setLabelFloat(true);

                tfIPrecio = new JFXTextField();
                tfIPrecio.setPromptText("Precio");
                tfIPrecio.setLabelFloat(true);

                tfIStock = new JFXTextField();
                tfIStock.setPromptText("Stock");
                tfIStock.setLabelFloat(true);

                tfICategoria = new JFXTextField();
                tfICategoria.setPromptText("Categoría");
                tfICategoria.setLabelFloat(true);

                HBox ctnBotones = new HBox(15);
                {
                    Button btnAceptar = new Button("Aceptar");
                    btnAceptar.setOnAction(aceptarInsercioActionListener(root, fondo));
                    Button btnCancelar = new Button("Cancelar");
                    btnCancelar.setOnAction((t) -> {
                        root.getChildren().remove(fondo);
                    });
                    ctnBotones.getChildren().addAll(btnCancelar, btnAceptar);
                }
                Contenedor.getChildren().addAll(tfIDescripcion, tfIPrecio, tfIStock, tfICategoria, ctnBotones);

                Contenedor.setPadding(new Insets(15));
                Contenedor.setStyle("-fx-background-color:rgb(235,235,235);-fx-background-radius:10px");
                Contenedor.setMaxSize(400, 270);
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

    public void formDatos(AnchorPane root, BorderPane layout) {
        VBox contenedor = new VBox(10);
        {

            HBox cntTitle = new HBox();
            {
                Label lblTitle = new Label("Productos");
                lblTitle.setStyle("-fx-text-fill:white;-fx-padding:5");
                cntTitle.getChildren().add(lblTitle);
                cntTitle.setAlignment(Pos.CENTER);
                cntTitle.setStyle("-fx-background-color:rgb(0,92,150)");
            }

            VBox TFContainer = new VBox(30);
            {
                tfDescripcion = new JFXTextField();
                tfDescripcion.setPromptText("Descripción");
                tfDescripcion.setLabelFloat(true);

                tfPrecio = new JFXTextField();
                tfPrecio.setPromptText("Precio");
                tfPrecio.setLabelFloat(true);

                tfStock = new JFXTextField();
                tfStock.setPromptText("Stock");
                tfStock.setLabelFloat(true);

                tfCategoria = new JFXTextField();
                tfCategoria.setPromptText("Categoría");
                tfCategoria.setLabelFloat(true);

                TFContainer.setStyle("-fx-padding:10");
                TFContainer.getChildren().addAll(tfDescripcion, tfPrecio, tfStock, tfCategoria);
            }

            HBox boxButtons = new HBox(10);
            {
                Button btnNuevo = new Button("Nuevo");
                btnNuevo.setOnAction((t) -> {
                    formInsertar(root);
                });
                Button btnModificar = new Button("Modificar");
                btnModificar.setOnAction(modificarActionListener());

                Button btnBuscar = new Button("Buscar");
                btnBuscar.setOnAction(buscarActionListener());

                Button btnEliminar = new Button("Eliminar");
                btnEliminar.setOnAction(eliminarActionListener());

                Button btnLimpiar = new Button("Limpiar");
                btnLimpiar.setOnAction(limpiarActionListener());

                boxButtons.setStyle("-fx-background-color:rgb(0,92,150);-fx-padding:5");
                boxButtons.getChildren().addAll(btnNuevo, btnModificar, btnBuscar, btnEliminar, btnLimpiar);
            }

            VBox.setVgrow(TFContainer, Priority.ALWAYS);
            contenedor.setStyle("-fx-background-color:white");

            contenedor.getChildren().addAll(cntTitle, TFContainer, boxButtons);
            contenedor.setAlignment(Pos.TOP_CENTER);
            layout.setRight(contenedor);
        }

    }

    public void formTablas(BorderPane contenedor) {
        contenedor.setCenter(Tabla());

    }

    public void launchTablas() {
        TableView<Producto> tabla = Tabla();
        AnchorPane root = new AnchorPane(tabla);
        AnchorPane.setTopAnchor(tabla, 0.0);
        AnchorPane.setBottomAnchor(tabla, 0.0);
        AnchorPane.setRightAnchor(tabla, 0.0);
        AnchorPane.setLeftAnchor(tabla, 0.0);

        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Productos");
        stage.show();

    }

    private static TableView<Producto> Tabla() {
        TableView<Producto> tabla = new TableView<>();
        //Name column
        TableColumn<Producto, String> nameColumn = new TableColumn<>("Descripción");
        nameColumn.setMinWidth(80);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));

        //Price column
        TableColumn<Producto, Double> priceColumn = new TableColumn<>("Precio");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Precio"));

        //Quantity column
        TableColumn<Producto, String> quantityColumn = new TableColumn<>("Stock");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Stock"));

        //Phone column
        TableColumn<Producto, String> phoneColumn = new TableColumn<>("Categoría");
        phoneColumn.setMinWidth(100);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Categoria"));

        tabla.setItems(getProducto());
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tabla.getColumns().addAll(nameColumn, priceColumn, quantityColumn, phoneColumn);
        return tabla;
    }

    private static ObservableList<Producto> getProducto() {
        ObservableList<Producto> lista = FXCollections.observableArrayList();
        IProducto sqlProducto = new ProductoImp();

        try {
            List<Producto> producto = sqlProducto.obtener();
            if (producto.size() > 0) {
                producto.forEach((tmp) -> {
                    lista.add(tmp);
                });
            }
        } catch (Exception e) {
            Mensaje("ERROR1", e.getMessage());
        }
        return lista;
    }

    /**
     * *************************************************************************
     *                                                                         *
     * IMPLEMENTACION DE LOS EVETOS * *
     * *************************************************************************
     */
    private EventHandler aceptarInsercioActionListener(AnchorPane root, StackPane fondo) {
        EventHandler handler = (t) -> {
            IProducto sqlProducto = new ProductoImp();
            int insertados = 0;
            try {
                ICategoria dao = new CategoriaImp();
                Producto producto = new Producto();
                producto.setDescripcion(tfIDescripcion.getText());
                producto.setPrecio(Double.parseDouble(tfIPrecio.getText()));
                producto.setStock(Integer.parseInt(tfIStock.getText()));
                producto.setCategoria(dao.obtener(tfIDescripcion.getText()));
                insertados = sqlProducto.insertar(producto);
                if (insertados > 0) {
                    root.getChildren().remove(fondo);
                    Mensaje("Insercion", "Nuevo producto guardado");
                }
            } catch (Exception e) {
                Mensaje("Error!", e.getMessage());
            }
        };
        return handler;
    }

    private EventHandler buscarActionListener() {
        EventHandler handler = (t) -> {
            try {
                IProducto sqlProducto = new ProductoImp();
                Producto producto = sqlProducto.obtener(Integer.parseInt(tfDescripcion.getText()));
                tfPrecio.setText(String.valueOf(producto.getPrecio()));
                tfIStock.setText(String.valueOf(producto.getStock()));
                tfCategoria.setText(producto.getCategoria().getDescripcion());
            } catch (Exception e) {
                Mensaje("ERROR!", e.getMessage());
            }
        };
        return handler;
    }

    private EventHandler modificarActionListener() {
        EventHandler handler = (t) -> {
            try {
                IProducto sqlProducto = new ProductoImp();
                ICategoria dao = new CategoriaImp();
                Producto producto = new Producto();
                producto.setDescripcion(tfDescripcion.getText());
                producto.setPrecio(Integer.parseInt(tfPrecio.getText()));
                producto.setPrecio(Integer.parseInt(tfStock.getText()));
                producto.setCategoria(dao.obtener(tfCategoria.getText()));
                if (sqlProducto.modificar(producto) > 0) {
                    Mensaje("Modificar", "Modificación exitosa");
                }
            } catch (Exception e) {
                Mensaje("ERROR!", e.getMessage());
            }
        };
        return handler;
    }

    private EventHandler eliminarActionListener() {
        EventHandler handler = (t) -> {
            try {
                IProducto sqlProducto = new ProductoImp();
                ICategoria dao = new CategoriaImp();
                Producto producto = new Producto();
                producto.setDescripcion(tfDescripcion.getText());
                producto.setPrecio(Integer.parseInt(tfPrecio.getText()));
                producto.setStock(Integer.parseInt(tfStock.getText()));
                producto.setCategoria(dao.obtener(tfCategoria.getText()));
                if (sqlProducto.eliminar(producto) > 0) {
                    Mensaje("Eliminar", "Eliminacionación exitosa");
                }
            } catch (Exception e) {
                Mensaje("ERROR!", e.getMessage());
            }
        };
        return handler;
    }

    private EventHandler limpiarActionListener() {
        EventHandler handler = (t) -> {
            tfDescripcion.setText("");
            tfIPrecio.setText("");
            tfStock.setText("");
            tfCategoria.setText("");
        };
        return handler;
    }

    private static void Mensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        //alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }

}
