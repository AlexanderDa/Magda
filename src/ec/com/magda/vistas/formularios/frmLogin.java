/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.magda.vistas.formularios;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import ec.com.magda.dao.contrato.IEmpleado;
import ec.com.magda.dao.impl.impEmpleado;
import ec.com.magda.rnegocio.entidades.Empleado;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author alexander
 */
public class frmLogin extends Application {

    private int Intentos = 3;

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        {

            JFXTextField txCedula = new JFXTextField("048572097-6");
            txCedula.setPromptText("Número de cédula");
            txCedula.setLabelFloat(true);
            txCedula.setLayoutY(175);
            AnchorPane.setLeftAnchor(txCedula, 25.0);
            AnchorPane.setRightAnchor(txCedula, 25.0);

            ImageView ivCheck = new ImageView();
            ivCheck.setFitHeight(25);
            ivCheck.setFitWidth(25);
            ivCheck.setLayoutY(175);
            AnchorPane.setRightAnchor(ivCheck, 25.0);

            StackPane cnrDilogo = new StackPane();
            JFXDialog dialogo = new JFXDialog();
            Label msm = new Label("Cédula incorrecta");
            dialogo.setContent(msm);
            cnrDilogo.setLayoutY(210);
            cnrDilogo.setLayoutX(95);
            cnrDilogo.setAlignment(Pos.CENTER);
//            AnchorPane.setLeftAnchor(cnrDilogo, 25.0);
//            AnchorPane.setRightAnchor(cnrDilogo, 25.0);

            Button btnLogin = new Button("Login");
            btnLogin.setDefaultButton(true);
            AnchorPane.setBottomAnchor(btnLogin, 25.0);
            AnchorPane.setRightAnchor(btnLogin, 25.0);

            root.getChildren().addAll(txCedula, ivCheck, cnrDilogo, btnLogin);

            btnLogin.setOnAction((t) -> {
                IEmpleado sqleEmpleado = new impEmpleado();

                try {
                    Empleado empleado = sqleEmpleado.obtener(txCedula.getText());
                    System.out.println("Inicio de sesión: " + empleado.getApellidos() + " " + empleado.getNombres());

                    frmPrincipal principal = new frmPrincipal();
                    Stage stage = new Stage();
                    principal.start(stage);
                    primaryStage.close();
                } catch (Exception e) {
                    System.err.println("No exite el empleado, número de intento:  " + Intentos);
                    dialogo.show(cnrDilogo);

                    Intentos -= 1;
                    if (Intentos == 0) {
                        primaryStage.close();
                    }
                    txCedula.setText("");
                }

            });
            
            root.getStyleClass().add("root");

        }
        Scene scene = new Scene(root, 300, 300);
        scene.setFill(new Color(0, 0, 0, 0));

        scene.getStylesheets().addAll(this.getClass().getResource("estilos/Login.css").toExternalForm());
        primaryStage.setTitle("Magda");
        //primaryStage.getIcons().add(new Image(getClass().getResource("../imagenes/Icono.png").toExternalForm()));
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
