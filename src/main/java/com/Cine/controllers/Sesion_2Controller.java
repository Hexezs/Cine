package com.Cine.controllers;
import com.Cine.MainApplication;
import com.Cine.SharedData;
import com.Cine.models.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class Sesion_2Controller {

    @FXML
    private Button BtnAtras;

    @FXML
    private Button BtnCancelar;

    @FXML
    private Button BtnSig;

    @FXML
    private ComboBox<String> CmbxUsuario;

    @FXML
    private TextField TextCorreo;

    @FXML
    private PasswordField TextContra;

    @FXML
    public void initialize() {
        CmbxUsuario.getItems().addAll("Admin", "Usuario");
    }

    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        TextCorreo.clear();
        TextContra.clear();
        CmbxUsuario.getSelectionModel().clearSelection();
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Inicio_1.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());

        stage.setTitle("CineSync - Inicio");
        stage.setScene(nextScene);
    }

    @FXML
    private void BtnCancelarAction() {
        TextCorreo.clear();
        TextContra.clear();
        CmbxUsuario.getSelectionModel().clearSelection();
        CmbxUsuario.setPromptText("Seleccione Usuario");
        System.out.println("Formulario limpiado");
    }

    @FXML
    private void BtnSigAction(ActionEvent actionEvent) throws IOException {
        String rol = CmbxUsuario.getValue();
        String correoIngresado = TextCorreo.getText();
        String contraIngresada = TextContra.getText();
        String vista = "";

        if (rol == null || TextCorreo.getText().isEmpty()) {
            System.out.println("Error: Rellena los campos");
            return;
        }
        if (rol.equalsIgnoreCase("Admin")) {
            vista = "views/Admin_6.fxml";
        } else {
            vista = "views/Use_4.fxml";
        }
        SharedData data = SharedData.getInstance();
        System.out.println("Acceso como Administrador detectado");

        if (rol.equals("Admin")) {
            System.out.println("Acceso como Administrador detectado");
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(vista));
            Scene scene = ((Button) actionEvent.getSource()).getScene();
            Stage stage = (Stage) scene.getWindow();
            Scene nextScene = new Scene(fxmlLoader.load());
            stage.setTitle("CineSync - Panel de " + rol);
            stage.setScene(nextScene);
            System.out.println("Pantalla de " + rol + " cargada correctamente.");

        } else if (rol.equals("Usuario")) {
            Usuario registrado = data.getUsuarioLogueado();

            if (registrado != null &&
                    correoIngresado.equals(registrado.getCorreo()) &&
                    contraIngresada.equals(registrado.getPassword())) {

                System.out.println("Acceso concedido: " + registrado.getNombre());
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(vista));
                Scene scene = ((Button) actionEvent.getSource()).getScene();
                Stage stage = (Stage) scene.getWindow();
                Scene nextScene = new Scene(fxmlLoader.load());
                stage.setTitle("CineSync - Panel de " + rol);
                stage.setScene(nextScene);
                System.out.println("Pantalla de " + rol + " cargada correctamente.");
            } else {
                System.out.println("Error: Los datos no coinciden con el usuario registrado");
            }
        }
    }

    public void CmbxUsuarioAction(ActionEvent actionEvent) {
    }
}
