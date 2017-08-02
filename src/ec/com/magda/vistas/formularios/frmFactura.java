/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.formularios;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import ec.com.magda.dao.contrato.ICliente;
import ec.com.magda.dao.contrato.IDetalle;
import ec.com.magda.dao.contrato.IEmpleado;
import ec.com.magda.dao.impl.impCliente;
import ec.com.magda.dao.impl.impDetalle;
import ec.com.magda.dao.impl.impEmpleado;
import ec.com.magda.rnegocio.entidades.Detalle;
import ec.com.magda.rnegocio.entidades.Empleado;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author alexander
 */
public class frmFactura {

    private Empleado empleado;
    private Detalle detalle;
    private JFXTextField tfCedula;
    private JFXTextField tfNombre;
    private JFXTextField tfApellido;
    private JFXTextField tfTelefono;
    private JFXTextField tfICedula;
    private JFXTextField tfINombre;
    private JFXTextField tfIApellido;
    private JFXTextField tfITelefono;
    private JFXDatePicker picker;
    private TextField tfNumeroFactura;
    private TextField tfFecha;
    private TextField tfEmpleado;
    private TextField tfCedulaDetalle;
    private TextField tfDetalle;
    private TextField tfTelefonoDetalle;

    public void formInsertar(AnchorPane root) {
        StackPane fondo = new StackPane();
        {

            VBox Contenedor = new VBox(25);
            {
                tfICedula = new JFXTextField("060405974-4");
                tfICedula.setPromptText("Cédula");
                tfICedula.setLabelFloat(true);

                tfINombre = new JFXTextField();
                tfINombre.setPromptText("Nombres");
                tfINombre.setLabelFloat(true);

                tfIApellido = new JFXTextField();
                tfIApellido.setPromptText("Apellidos");
                tfIApellido.setLabelFloat(true);

                tfITelefono = new JFXTextField();
                tfITelefono.setPromptText("Teléfono");
                tfITelefono.setLabelFloat(true);

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
                Contenedor.getChildren().addAll(tfICedula, tfIApellido, tfINombre, tfITelefono, ctnBotones);

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
                Label lblTitle = new Label("Factúra");
                lblTitle.setStyle("-fx-text-fill:white;-fx-padding:5");
                cntTitle.getChildren().add(lblTitle);
                cntTitle.setAlignment(Pos.CENTER);
                cntTitle.setStyle("-fx-background-color:rgb(0,92,150)");
            }

            VBox TFContainer = new VBox(30);
            {
                tfCedula = new JFXTextField();
                tfCedula.setOnKeyReleased((t) -> {
                    ICliente sqlCliente = new impCliente();
                    if (tfCedula.getText().length() == 11) {
                        try {
                            System.err.println("a");
                        } catch (Exception e) {
                        }
                    }
                });
                tfCedula.setPromptText("Cedula del cliente");
                tfCedula.setLabelFloat(true);
                TFContainer.setStyle("-fx-padding:10");
                TFContainer.getChildren().addAll(tfCedula);
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

    public void formFacturacion(BorderPane contenedor) {
        AnchorPane panel = new AnchorPane();
        {
            final Label lblFactura = new Label("FACTURA");
            lblFactura.setAlignment(Pos.CENTER);
            AnchorPane.setLeftAnchor(lblFactura, 0.0);
            AnchorPane.setRightAnchor(lblFactura, 0.0);

            VBox infoMagda = new VBox(10);
            {
                final Label lblDireccion = new Label("Dirección: ");
                final Label lblCiudad = new Label("Ciudad:    ");
                final Label lblTelefono = new Label("Teléfono:  ");
                infoMagda.getChildren().addAll(lblCiudad, lblDireccion, lblTelefono);
                infoMagda.setLayoutX(25);
                infoMagda.setLayoutY(50);

            }

            GridPane infoFactura = new GridPane();
            {
                final Label lblFecha = new Label("Fécha: ");
                final Label lblNumeroFactura = new Label("Factura #:    ");

                tfFecha = new TextField();
                tfFecha.setText(new Date().toString());
                tfNumeroFactura = new TextField();
                tfNumeroFactura.setEditable(false);
                tfNumeroFactura.setAlignment(Pos.CENTER_RIGHT);

                GridPane.setConstraints(lblFecha, 0, 0);
                GridPane.setConstraints(lblNumeroFactura, 0, 1);
                GridPane.setConstraints(tfFecha, 1, 0);
                GridPane.setConstraints(tfNumeroFactura, 1, 1);
                infoFactura.getChildren().addAll(lblFecha, lblNumeroFactura, tfFecha, tfNumeroFactura);
                infoFactura.setLayoutY(50);
                infoFactura.setVgap(5);
                AnchorPane.setRightAnchor(infoFactura, 25.0);
            }

            GridPane infoDetalle = new GridPane();
            {
                final Label lblEmpleado = new Label("Vendedor");
                final Label lblCedulaDetalle = new Label("Cédula");
                final Label lblDetalle = new Label("Detalle");
                final Label lblTelefonoDetalle = new Label("Teléfono");

                tfEmpleado = new TextField();
                tfEmpleado.setEditable(false);

                tfCedulaDetalle = new TextField("");
                tfCedulaDetalle.setEditable(false);
                tfDetalle = new TextField();
                tfDetalle.setEditable(false);

                tfTelefonoDetalle = new TextField();
                tfTelefonoDetalle.setEditable(false);

                GridPane.setConstraints(lblEmpleado, 0, 0);
                GridPane.setHgrow(tfEmpleado, Priority.ALWAYS);
                GridPane.setConstraints(lblCedulaDetalle, 0, 1);
                GridPane.setConstraints(lblDetalle, 0, 2);
                GridPane.setConstraints(lblTelefonoDetalle, 0, 3);
                GridPane.setConstraints(tfEmpleado, 1, 0);
                GridPane.setConstraints(tfCedulaDetalle, 1, 1);
                GridPane.setConstraints(tfDetalle, 1, 2);
                GridPane.setConstraints(tfTelefonoDetalle, 1, 3);
                infoDetalle.setVgap(5);
                infoDetalle.setHgap(10);
                infoDetalle.getChildren().addAll(lblCedulaDetalle, lblDetalle, lblTelefonoDetalle, lblEmpleado);
                infoDetalle.getChildren().addAll(tfCedulaDetalle, tfDetalle, tfTelefonoDetalle, tfEmpleado);
                infoDetalle.setLayoutY(150);
                AnchorPane.setLeftAnchor(infoDetalle, 25.0);
                AnchorPane.setRightAnchor(infoDetalle, 25.0);
            }

            panel.getChildren().addAll(lblFactura, infoMagda, infoFactura, infoDetalle, Tabla());

            contenedor.setCenter(panel);
        }

    }

    public void launchTablas() {
        TableView<Detalle> tabla = Tabla();
        AnchorPane root = new AnchorPane(tabla);
        AnchorPane.setTopAnchor(tabla, 0.0);
        AnchorPane.setBottomAnchor(tabla, 0.0);
        AnchorPane.setRightAnchor(tabla, 0.0);
        AnchorPane.setLeftAnchor(tabla, 0.0);

        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Detalles");
        stage.show();

    }

    private static TableView<Detalle> Tabla() {
        TableView<Detalle> tabla = new TableView<>();
        {
            //Name column
            TableColumn<Detalle, String> nameColumn = new TableColumn<>("Producto");
            nameColumn.setMinWidth(80);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("Cedula"));

            //Price column
            TableColumn<Detalle, Double> priceColumn = new TableColumn<>("Precio");
            priceColumn.setMinWidth(100);
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("Nombres"));

            //Quantity column
            TableColumn<Detalle, String> quantityColumn = new TableColumn<>("Cantidad");
            quantityColumn.setMinWidth(100);
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));

            //Phone column
            TableColumn<Detalle, String> phoneColumn = new TableColumn<>("Total");
            phoneColumn.setMinWidth(100);
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

            tabla.setItems(getDetalle());
            tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            tabla.getColumns().addAll(nameColumn, priceColumn, quantityColumn, phoneColumn);
            AnchorPane.setTopAnchor(tabla, 300.0);
            AnchorPane.setBottomAnchor(tabla, 50.0);
            AnchorPane.setRightAnchor(tabla, 20.0);
            AnchorPane.setLeftAnchor(tabla, 25.0);
        }
        return tabla;
    }

    private static ObservableList<Detalle> getDetalle() {
        ObservableList<Detalle> lista = FXCollections.observableArrayList();
        IDetalle sqlDetalle = new impDetalle();

        try {
            List<Detalle> detalles = sqlDetalle.obtener();
            if (detalles.size() > 0) {
                detalles.forEach((tmp) -> {
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
            IDetalle sqlDetalle = new impDetalle();
            int insertados = 0;
            try {

                insertados = sqlDetalle.insertar(detalle);
                if (insertados > 0) {
                    root.getChildren().remove(fondo);
                    Mensaje("Insercio", "Nuevo detalle guardado");
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
                IDetalle sqlDetalle = new impDetalle();

            } catch (Exception e) {
                Mensaje("ERROR!", e.getMessage());
            }
            System.out.println(tfApellido.getStyleClass());
        };
        return handler;
    }

    private EventHandler modificarActionListener() {
        EventHandler handler = (t) -> {
            try {
                IDetalle sqlDetalle = new impDetalle();
                Detalle detalle = new Detalle();

                if (sqlDetalle.modificar(detalle) > 0) {
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
                IDetalle sqlDetalle = new impDetalle();
                Detalle detalle = new Detalle();

                if (sqlDetalle.eliminar(detalle) > 0) {
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
            tfApellido.setText("");
            tfCedula.setText("");
            tfNombre.setText("");
            tfTelefono.setText("");
            System.out.println(empleado.getCedula());
            System.out.println(empleado.getApellidos());
            System.out.println(empleado.getNombres());
            System.out.println(empleado.getTelefono());

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

    public void setEmpleado(String cedulaEmpleado) {
        try {
            IEmpleado sqlEmpleado = new impEmpleado();
            empleado = sqlEmpleado.obtener(cedulaEmpleado);
            tfEmpleado.setText(empleado.getApellidos() + " " + empleado.getApellidos());
        } catch (Exception e) {
        }
    }

    public void setNumeroFactura(int numero) {
        tfNumeroFactura.setText("" + numero);
    }

}
