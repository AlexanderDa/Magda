/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.formularios;

import com.jfoenix.controls.JFXTextField;
import ec.com.magda.accesodatos.Conexion;
import ec.com.magda.dao.contrato.ICliente;
import ec.com.magda.dao.impl.ClienteImp;
import ec.com.magda.rnegocio.entidades.Cliente;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author alexander
 */
public class frmReportes {

    public void formInsertar(AnchorPane root) throws Exception {
        StackPane fondo = new StackPane();
        {

            VBox Contenedor = new VBox(25);
            {

                AnchorPane contenido = new AnchorPane();
                {
                    //Aqui el contenido de los reportes
                    Button btn = new Button("3 Mejores Clientes");
                    contenido.getChildren().addAll(btn/*Aqui los conponentes creados*/);
                    contenido.setStyle("-fx-background-color:red");
                }

                HBox ctnBotones = new HBox(15);
                {
                    Button btnAceptar = new Button("Aceptar");
                    btnAceptar.setOnAction(aceptarReporteActionListener(root, fondo));
                    Button btnCancelar = new Button("Cancelar");
                    btnCancelar.setOnAction((t) -> {
                        root.getChildren().remove(fondo);
                    });
                    ctnBotones.getChildren().addAll(btnCancelar, btnAceptar);
                }
                Contenedor.getChildren().addAll(contenido, ctnBotones);

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

    /**
     * *************************************************************************
     *                                                                         *
     * IMPLEMENTACION DE LOS EVETOS * *
     * *************************************************************************
     */
    private EventHandler aceptarReporteActionListener(AnchorPane root, StackPane fondo) throws Exception {
        EventHandler handler = (Event t) -> {
            Conexion con = new Conexion();
            JasperReport reporte = null; //Creo el objeto reporte
            // Ubicacion del Reporte
            String path = "C:\\Users\\acer1\\Documents\\NetBeansProjects\\Magda\\src\\ec\\com\\magda\\vistas\\reportes\\3_mejores_clientes.jasper";
            try {
                Connection cn = con.conectar();
                reporte = (JasperReport) JRLoader.loadObjectFromFile(path); //Cargo el reporte al objeto
                JasperPrint jprint = JasperFillManager.fillReport(path, null, cn); //Llenado del Reporte con Tres parametros ubicacion,parametros,conexion a la base de datos
                JasperViewer viewer = new JasperViewer(jprint, false); //Creamos la vista del Reporte
                viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Le agregamos que se cierre solo el reporte cuando lo cierre el usuario
                viewer.setVisible(true); //Inicializamos la vista del Reporte
                root.getChildren().remove(fondo);
            } catch (JRException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(frmReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        return handler;
    }
}
