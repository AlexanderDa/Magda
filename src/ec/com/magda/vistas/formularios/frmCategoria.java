/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.formularios;

import com.jfoenix.controls.JFXTextField;
import ec.com.magda.dao.contrato.ICategoria;
import ec.com.magda.dao.impl.CategoriaImp;
import ec.com.magda.rnegocio.entidades.Categoria;
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
 * @author alexander
 */
public class frmCategoria{
    
    private JFXTextField tfId;
    private JFXTextField tfDescripcion;
    private JFXTextField tfIId;
    private JFXTextField tfIDescripcion;
    
    public void formInsertar(AnchorPane root) {
        StackPane fondo = new StackPane();
        {
            
            VBox Contenedor = new VBox(25);
            {
                tfIId = new JFXTextField("");
                tfIId.setPromptText("Id");
                tfIId.setLabelFloat(true);
                
                tfIDescripcion = new JFXTextField();
                tfIDescripcion.setPromptText("Descripción");
                tfIDescripcion.setLabelFloat(true);
                
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
                Contenedor.getChildren().addAll(tfIId, tfIDescripcion, ctnBotones);
                
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
                Label lblTitle = new Label("Categorías");
                lblTitle.setStyle("-fx-text-fill:white;-fx-padding:5");
                cntTitle.getChildren().add(lblTitle);
                cntTitle.setAlignment(Pos.CENTER);
                cntTitle.setStyle("-fx-background-color:rgb(0,92,150)");
            }
            
            VBox TFContainer = new VBox(30);
            {
                
                tfId = new JFXTextField();
                tfId.setPromptText("Id");
                tfId.setLabelFloat(true);
                
                tfDescripcion = new JFXTextField();
                tfDescripcion.setPromptText("Descripción");
                tfDescripcion.setLabelFloat(true);

                TFContainer.setStyle("-fx-padding:10");
                TFContainer.getChildren().addAll(tfId, tfDescripcion);
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
        TableView<Categoria> tabla = Tabla();
        AnchorPane root = new AnchorPane(tabla);
        AnchorPane.setTopAnchor(tabla, 0.0);
        AnchorPane.setBottomAnchor(tabla, 0.0);
        AnchorPane.setRightAnchor(tabla, 0.0);
        AnchorPane.setLeftAnchor(tabla, 0.0);
        
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Categoría");
        stage.show();
        
    }
    
    private static TableView<Categoria> Tabla() {
        TableView<Categoria> tabla = new TableView<>();
        //Name column
        TableColumn<Categoria, String> nameColumn = new TableColumn<>("Id");
        nameColumn.setMinWidth(80);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        //Price column
        TableColumn<Categoria, Double> priceColumn = new TableColumn<>("Descripción");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));

        tabla.setItems(getCategoria());
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        tabla.getColumns().addAll(nameColumn, priceColumn);
        return tabla;
    }
    
    private static ObservableList<Categoria> getCategoria() {
        ObservableList<Categoria> lista = FXCollections.observableArrayList();
        ICategoria sqlCategoria = new CategoriaImp();
        
        try {
            List<Categoria> categorias = sqlCategoria.obtener();
            if (categorias.size() > 0) {
                categorias.forEach((tmp) -> {
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
            ICategoria sqlCategoria = new CategoriaImp();
            int insertados = 0;
            try {
                Categoria categoria = new Categoria();
                categoria.setId(Integer.parseInt(tfIId.getText()));
                categoria.setDescripcion(tfIDescripcion.getText());
                insertados = sqlCategoria.insertar(categoria);
                if (insertados > 0) {
                    root.getChildren().remove(fondo);
                    Mensaje("Insercion", "Nueva categoria guardada");
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
                ICategoria sqlCategoria = new CategoriaImp();
                Categoria categoria = sqlCategoria.obtener(Integer.parseInt(tfId.getText()));
                tfDescripcion.setText(categoria.getDescripcion());
            } catch (Exception e) {
                Mensaje("ERROR!", e.getMessage());
            }
        };
        return handler;
    }
    
    private EventHandler modificarActionListener() {
        EventHandler handler = (t) -> {
            try {
                ICategoria sqlCategoria = new CategoriaImp();
                Categoria categoria = new Categoria();
                categoria.setDescripcion(tfDescripcion.getText());
                if (sqlCategoria.modificar(categoria) > 0) {
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
                ICategoria sqlCategoria = new CategoriaImp();
                Categoria categoria = new Categoria();
                categoria.setId(Integer.parseInt(tfId.getText()));
                categoria.setDescripcion(tfDescripcion.getText());
                if (sqlCategoria.eliminar(categoria) > 0) {
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
            tfId.setText("");
            tfDescripcion.setText("");
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