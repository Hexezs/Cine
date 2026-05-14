package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.dto.UsuarioInicioDTO;
import com.Cine.models.Usuario;
import com.Cine.services.UsuarioService;

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
import java.util.Optional;

public class Sesion_2Controller {

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML private Button BtnAtras;
    @FXML private Button BtnCancelar;
    @FXML private Button BtnSig;

    @FXML private ComboBox<String> CmbxUsuario;

    @FXML private TextField TextCorreo;

    @FXML private PasswordField TextContra;

    @FXML
    public void initialize() {

        CmbxUsuario.getItems().addAll("Admin", "Usuario");
    }

    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        limpiarCampos();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Inicio_1.fxml"));

        Scene nextScene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();

        stage.setTitle("CineSync - Inicio");
        stage.setScene(nextScene);
    }

    @FXML
    private void BtnCancelarAction() {
        limpiarCampos();
    }

    @FXML
    private void BtnSigAction(ActionEvent actionEvent) throws IOException {

        String rolSeleccionado = CmbxUsuario.getValue();

        String correo = TextCorreo.getText();

        String password = TextContra.getText();

        if (rolSeleccionado == null || correo.isEmpty() || password.isEmpty()) {

            System.out.println("Error: Rellena todos los campos");

            return;
        }

        UsuarioInicioDTO loginData = new UsuarioInicioDTO(correo, password, rolSeleccionado);

        Optional<Usuario> usuarioOpt = usuarioService.iniciarSesion(loginData);

        if (usuarioOpt.isPresent()) {
            Usuario usuarioLogueado = usuarioOpt.get();
            System.out.println("Bienvenido " + usuarioLogueado.getNombre());

            if (rolSeleccionado.equals("Admin")) {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Admin_6.fxml"));
                Scene nextScene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Panel Administrador");
                stage.setScene(nextScene);

            } else {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Use_4.fxml"));

                Scene nextScene = new Scene(fxmlLoader.load());

                Use_4Controller controller = fxmlLoader.getController();

                controller.setUsuarioLogueado(usuarioLogueado);

                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                stage.setTitle("Cartelera");
                stage.setScene(nextScene);
            }

        } else {
            System.out.println("Error: Credenciales incorrectas");
        }
    }

    @FXML
    public void CmbxUsuarioAction(ActionEvent actionEvent) {
    }

    private void limpiarCampos() {
        TextCorreo.clear();
        TextContra.clear();
        CmbxUsuario.getSelectionModel().clearSelection();
    }
}