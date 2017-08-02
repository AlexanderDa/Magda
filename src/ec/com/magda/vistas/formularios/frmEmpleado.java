/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.formularios;

import com.jfoenix.controls.JFXTextField;
import ec.com.magda.dao.contrato.ICliente;
import ec.com.magda.dao.contrato.IEmpleado;
import ec.com.magda.dao.impl.ClienteImp;
import ec.com.magda.dao.impl.EmpleadoImp;
import ec.com.magda.rnegocio.entidades.Cliente;
import ec.com.magda.rnegocio.entidades.Empleado;
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
public class frmEmpleado {

    private JFXTextField tfCedula;
    private JFXTextField tfNombre;
    private JFXTextField tfApellido;
    private JFXTextField tfTelefono;
    private JFXTextField tfSueldo;
    private JFXTextField tfDireccion;
    private JFXTextField tfICedula;
    private JFXTextField tfINombre;
    private JFXTextField tfIApellido;
    private JFXTextField tfITelefono;
    private JFXTextField tfIDireccion;
    private JFXTextField tfISueldo;

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

                tfIDireccion = new JFXTextField();
                tfIDireccion.setPromptText("Dirección");
                tfIDireccion.setLabelFloat(true);

                tfISueldo = new JFXTextField();
                tfISueldo.setPromptText("Sueldo");
                tfISueldo.setLabelFloat(true);

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
                Contenedor.getChildren().addAll(tfICedula, tfIApellido, tfINombre, tfITelefono, tfIDireccion, tfISueldo, ctnBotones);

                Contenedor.setPadding(new Insets(15));
                Contenedor.setStyle("-fx-background-color:rgb(235,235,235);-fx-background-radius:10px");
                Contenedor.setMaxSize(400, 300);
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
                Label lblTitle = new Label("Empleados");
                lblTitle.setStyle("-fx-text-fill:white;-fx-padding:5");
                cntTitle.getChildren().add(lblTitle);
                cntTitle.setAlignment(Pos.CENTER);
                cntTitle.setStyle("-fx-background-color:rgb(0,92,150)");
            }

            VBox TFContainer = new VBox(30);
            {

                tfCedula = new JFXTextField();
                tfCedula.setPromptText("Cédula");
                tfCedula.setLabelFloat(true);

                tfNombre = new JFXTextField();
                tfNombre.setPromptText("Nombres");
                tfNombre.setLabelFloat(true);

                tfApellido = new JFXTextField();
                tfApellido.setPromptText("Apellidos");
                tfApellido.setLabelFloat(true);

                tfTelefono = new JFXTextField();
                tfTelefono.setPromptText("Teléfono");
                tfTelefono.setLabelFloat(true);
                TFContainer.setStyle("-fx-padding:10");

                tfDireccion = new JFXTextField();
                tfDireccion.setPromptText("Dirección");
                tfDireccion.setLabelFloat(true);
                TFContainer.setStyle("-fx-padding:10");

                tfSueldo = new JFXTextField();
                tfSueldo.setPromptText("Sueldo");
                tfSueldo.setLabelFloat(true);
                TFContainer.setStyle("-fx-padding:10");

                TFContainer.getChildren().addAll(tfCedula, tfNombre, tfApellido, tfTelefono, tfDireccion, tfSueldo);
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
        TableView<Empleado> tabla = Tabla();
        AnchorPane root = new AnchorPane(tabla);
        AnchorPane.setTopAnchor(tabla, 0.0);
        AnchorPane.setBottomAnchor(tabla, 0.0);
        AnchorPane.setRightAnchor(tabla, 0.0);
        AnchorPane.setLeftAnchor(tabla, 0.0);

        Scene scene = new Scene(root, 700, 450);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Clientes");
        stage.show();

    }

    private static TableView<Empleado> Tabla() {
        TableView<Empleado> tabla = new TableView<>();
        //Name column
        TableColumn<Empleado, String> colCedula = new TableColumn<>("Cedula");
        colCedula.setMinWidth(80);
        colCedula.setCellValueFactory(new PropertyValueFactory<>("Cedula"));

        //Price column
        TableColumn<Empleado, Double> colNonbres = new TableColumn<>("Nombres");
        colNonbres.setMinWidth(100);
        colNonbres.setCellValueFactory(new PropertyValueFactory<>("Nombres"));

        //Quantity column
        TableColumn<Empleado, String> colApellidos = new TableColumn<>("Apellidos");
        colApellidos.setMinWidth(100);
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));

        //Phone column
        TableColumn<Empleado, String> colTelefono = new TableColumn<>("Teléfono");
        colTelefono.setMinWidth(100);
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

        TableColumn<Empleado, String> colDireccion = new TableColumn<>("Direccion");
        colDireccion.setMinWidth(100);
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<Empleado, String> colSueldo = new TableColumn<>("Sueldo");
        colSueldo.setMinWidth(100);
        colSueldo.setCellValueFactory(new PropertyValueFactory<>("sueldo"));

        tabla.setItems(getCliente());
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tabla.getColumns().addAll(colCedula, colApellidos, colNonbres, colDireccion, colTelefono, colSueldo);
        return tabla;
    }

    private static ObservableList<Empleado> getCliente() {
        ObservableList<Empleado> lista = FXCollections.observableArrayList();

        try {
            IEmpleado sqlEmpleado = new EmpleadoImp();
            List<Empleado> empleados = sqlEmpleado.obtener();
            if (empleados.size() > 0) {
                empleados.forEach((tmp) -> {
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
            IEmpleado sqlEmpleado = new EmpleadoImp();

            try {
                Empleado empleado = new Empleado();
                empleado.setApellidos(tfIApellido.getText());
                empleado.setCedula(tfICedula.getText());
                empleado.setNombres(tfINombre.getText());
                empleado.setTelefono(tfITelefono.getText());
                empleado.setDireccion(tfIDireccion.getText());
                empleado.setSueldo(Double.parseDouble(tfISueldo.getText()));

                if (sqlEmpleado.insertar(empleado) > 0) {
                    root.getChildren().remove(fondo);
                    Mensaje("Insercion", "Nuevo empleado guardado");
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
                IEmpleado sqlEmpleado = new EmpleadoImp();
                Empleado empleado = sqlEmpleado.obtener(tfCedula.getText());
                tfApellido.setText(empleado.getApellidos());
                tfNombre.setText(empleado.getNombres());
                tfTelefono.setText(empleado.getTelefono());
                tfDireccion.setText(empleado.getDireccion());
                tfSueldo.setText("" + empleado.getSueldo());
            } catch (Exception e) {
                Mensaje("ERROR!", e.getMessage());
            }
        };
        return handler;
    }

    private EventHandler modificarActionListener() {
        EventHandler handler = (t) -> {
            IEmpleado sqlEmpleado = new EmpleadoImp();
            Empleado empleado = new Empleado();
            try {

                empleado.setApellidos(tfApellido.getText());
                empleado.setCedula(tfCedula.getText());
                empleado.setNombres(tfNombre.getText());
                empleado.setTelefono(tfTelefono.getText());
                empleado.setDireccion(tfDireccion.getText());
                empleado.setSueldo(Double.parseDouble(tfSueldo.getText()));
                System.out.println(""
                        + "Cedula: " + empleado.getCedula()
                        + "Nombre: " + empleado.getNombres()
                        + "Apellido: " + empleado.getApellidos()
                        + "telefono: " + empleado.getTelefono()
                        + "dir: " + empleado.getDireccion()
                        + "Sueldo: " + empleado.getSueldo()
                );
                int modificado = sqlEmpleado.modificar(empleado);
                if (modificado > 0) {
                    Mensaje("Actualizacion", "Cambios Guardados");
                }
            } catch (Exception e) {
                Mensaje("Error!", e.getMessage());
                System.err.printf("Error en la modificacion de empleado: " + e.getMessage());
            }
        };
        return handler;
    }

    private EventHandler eliminarActionListener() {
        EventHandler handler = (t) -> {
            try {
                IEmpleado sqlEmpleado = new EmpleadoImp();
                Empleado empleado = new Empleado();
                empleado.setCedula(tfCedula.getText());
                if (sqlEmpleado.eliminar(empleado) > 0) {
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
            tfDireccion.setText("");
            tfSueldo.setText("");
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
