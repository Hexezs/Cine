package com.Cine.controllers;

import com.Cine.MainApplication;
import com.Cine.dto.UsuarioRegistroDTO;
import com.Cine.models.Usuario;
import com.Cine.services.UsuarioService;

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
    private TextField TextApellidoP;
    @FXML
    private TextField TextApellidoM;
    @FXML
    private Button BtnAtras;

    @FXML
    private Button BtnCancelar;

    @FXML
    private Button BtnSig;

    @FXML
    private TextField TextNombre;
    @FXML
    private TextField TextCorreo;

    @FXML
    private PasswordField TextContra;

    private final UsuarioService usuarioService = new UsuarioService();

    private Usuario usuarioEditar;

    public void setUsuarioEditar(Usuario usuario) {
        this.usuarioEditar = usuario;
        TextNombre.setText(usuario.getNombre());
        TextApellidoP.setText(usuario.getApellidoP());
        TextApellidoM.setText(usuario.getApellidoM());
        TextCorreo.setText(usuario.getCorreo());
        TextContra.setText(usuario.getPassword());
        BtnSig.setText("Guardar Cambios");
    }

    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Inicio_1.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("CineSync - Inicio");
        stage.setScene(nextScene);
    }

    @FXML
    private void BtnCancelarAction() {
        TextNombre.clear();
        TextApellidoM.clear();
        TextApellidoP.clear();
        TextCorreo.clear();
        TextContra.clear();
    }

    @FXML
    private void BtnSigAction(ActionEvent actionEvent) throws IOException {

        if (TextNombre.getText().isEmpty() || TextApellidoP.getText().isEmpty()|| TextApellidoM.getText().isEmpty() || TextCorreo.getText().isEmpty() || TextContra.getText().isEmpty()) {
            System.out.println("Error: Rellena todos los campos");
            return;
        }

        if (usuarioEditar != null) {

            usuarioEditar.setNombre(TextNombre.getText());

            usuarioEditar.setApellidoP(TextApellidoP.getText());

            usuarioEditar.setApellidoM(TextApellidoM.getText());

            usuarioEditar.setCorreo(TextCorreo.getText());

            usuarioEditar.setPassword(TextContra.getText());

            usuarioService.actualizarPerfil(usuarioEditar);

            System.out.println("Perfil actualizado");

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Use_4.fxml"));

            Scene nextScene = new Scene(fxmlLoader.load());Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("CineSync - Panel Usuario");
            stage.setScene(nextScene);

        } else {

            UsuarioRegistroDTO dto =
                    new UsuarioRegistroDTO(TextNombre.getText(), TextApellidoM.getText(), TextApellidoP.getText(), TextCorreo.getText(), TextContra.getText());

            usuarioService.registrarNuevoUsuario(dto);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));
            Scene nextScene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();

            stage.setTitle("CineSync - Iniciar Sesion");
            stage.setScene(nextScene);
        }
    }
}