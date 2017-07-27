/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.formularios;

import com.jfoenix.controls.JFXTextField;
import ec.com.magda.dao.contrato.ICliente;
import ec.com.magda.dao.impl.impCliente;
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
public class frmCliente {
    
    private JFXTextField tfCedula;
    private JFXTextField tfNombre;
    private JFXTextField tfApellido;
    private JFXTextField tfTelefono;
    private JFXTextField tfICedula;
    private JFXTextField tfINombre;
    private JFXTextField tfIApellido;
    private JFXTextField tfITelefono;
    
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
                Label lblTitle = new Label("Clientes");
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
                TFContainer.getChildren().addAll(tfCedula, tfNombre, tfApellido, tfTelefono);
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
        TableView<Cliente> tabla = Tabla();
        AnchorPane root = new AnchorPane(tabla);
        AnchorPane.setTopAnchor(tabla, 0.0);
        AnchorPane.setBottomAnchor(tabla, 0.0);
        AnchorPane.setRightAnchor(tabla, 0.0);
        AnchorPane.setLeftAnchor(tabla, 0.0);
        
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Clientes");
        stage.show();
        
    }
    
    private static TableView<Cliente> Tabla() {
        TableView<Cliente> tabla = new TableView<>();
        //Name column
        TableColumn<Cliente, String> nameColumn = new TableColumn<>("Cedula");
        nameColumn.setMinWidth(80);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Cedula"));

        //Price column
        TableColumn<Cliente, Double> priceColumn = new TableColumn<>("Nombres");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Nombres"));

        //Quantity column
        TableColumn<Cliente, String> quantityColumn = new TableColumn<>("Apellidos");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));

        //Phone column
        TableColumn<Cliente, String> phoneColumn = new TableColumn<>("Teléfono");
        phoneColumn.setMinWidth(100);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        
        tabla.setItems(getCliente());
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tabla.getColumns().addAll(nameColumn, priceColumn, quantityColumn, phoneColumn);
        return tabla;
    }
    
    private static ObservableList<Cliente> getCliente() {
        ObservableList<Cliente> lista = FXCollections.observableArrayList();
        ICliente sqlCliente = new impCliente();
        
        try {
            List<Cliente> clientes = sqlCliente.obtener();
            if (clientes.size() > 0) {
                clientes.forEach((tmp) -> {
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
            ICliente sqlCliente = new impCliente();
            int insertados = 0;
            try {
                Cliente cliente = new Cliente();
                cliente.setApellidos(tfIApellido.getText());
                cliente.setCedula(tfICedula.getText());
                System.out.println(cliente.getCedula());
                cliente.setNombres(tfINombre.getText());
                cliente.setTelefono(tfITelefono.getText());
                insertados = sqlCliente.insertar(cliente);
                if (insertados > 0) {
                    root.getChildren().remove(fondo);
                    Mensaje("Insercio", "Nuevo cliente guardado");
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
                ICliente sqlCliente = new impCliente();
                Cliente cliente = sqlCliente.obtener(tfCedula.getText());
                tfApellido.setText(cliente.getApellidos());
                tfNombre.setText(cliente.getNombres());
                tfTelefono.setText(cliente.getTelefono());
            } catch (Exception e) {
                Mensaje("ERROR!", e.getMessage());
            }
        };
        return handler;
    }
    
    private EventHandler modificarActionListener() {
        EventHandler handler = (t) -> {
            try {
                ICliente sqlCliente = new impCliente();
                Cliente cliente = new Cliente();
                cliente.setApellidos(tfApellido.getText());
                cliente.setCedula(tfCedula.getText());
                cliente.setNombres(tfNombre.getText());
                cliente.setTelefono(tfTelefono.getText());
                if (sqlCliente.modificar(cliente) > 0) {
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
                ICliente sqlCliente = new impCliente();
                Cliente cliente = new Cliente();
                cliente.setApellidos(tfApellido.getText());
                cliente.setCedula(tfCedula.getText());
                cliente.setNombres(tfNombre.getText());
                cliente.setTelefono(tfTelefono.getText());
                if (sqlCliente.eliminar(cliente) > 0) {
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
