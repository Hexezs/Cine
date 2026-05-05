package com.Cine.controllers;
import com.Cine.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreaCuenta_3Controller {

    @FXML
    private Button BtnAtras;

    @FXML
    private Button BtnCancelar;

    @FXML
    private Button BtnSig;

    @FXML
    private TextField TextNombre;

    @FXML
    private TextField TextApellido;

    @FXML
    private TextField TextCorreo;

    @FXML
    private PasswordField TextContra;

    @FXML
    public void initialize() {
        System.out.println("Pantalla crear cuenta cargada");
    }

    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        TextNombre.clear();
        TextApellido.clear();
        TextCorreo.clear();
        TextContra.clear();
        System.out.println("Click en Atrás");
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Inicio_1.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());

        stage.setTitle("CineSync - Inicio");
        stage.setScene(nextScene);
    }

    @FXML
    private void BtnCancelarAction() {
        System.out.println("Click en Cancelar");

        // Limpiar campos
        TextNombre.clear();
        TextApellido.clear();
        TextCorreo.clear();
        TextContra.clear();
    }

    @FXML
    private void BtnSigAction() {
        String nombre = TextNombre.getText();
        String apellido = TextApellido.getText();
        String correo = TextCorreo.getText();
        String contra = TextContra.getText();

        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Correo: " + correo);
        System.out.println("Contraseña: " + contra);

    }
}