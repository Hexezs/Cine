package com.Cine.controllers;
import com.Cine.MainApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class InicioController {
    @FXML
    private Button BtnSesion;

    @FXML
    private Button BtnRregistro;

    @FXML
    public void initialize() {
        System.out.println("Pantalla principal cargada");
    }

    @FXML
    private void BtnSesionAction(ActionEvent actionEvent) throws IOException {
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));

        Scene nextScene = new Scene(fxmlLoader.load());
        stage.setTitle("CineSync -Iniciar Sesión");
        stage.setScene(nextScene);
    }

    @FXML
    private void BtnRegistroAction(ActionEvent actionEvent) throws IOException{
        Scene scene3 = ((Button) actionEvent.getSource()).getScene();
        Stage stage3 = (Stage) scene3.getWindow();

        FXMLLoader fxmlLoader3 = new FXMLLoader(MainApplication.class.getResource("views/CreaCuenta_3.fxml"));

        Scene nextScene = new Scene(fxmlLoader3.load());
        stage3.setTitle("CineSync -Crear Cuenta");
        stage3.setScene(nextScene);
    }
}
