package com.Cine.controllers;
import com.Cine.MainApplication;
import com.Cine.SharedData;
import com.Cine.models.Usuario;
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
        SharedData data = SharedData.getInstance();

        // Si entramos en modo edición, precargamos los datos del ShareData
        if (data.isModoEdicion()) {
            System.out.println("Cargando vista en Modo Edición");
            BtnSig.setText("Guardar Cambios");

            Usuario user = data.getUsuarioLogueado();
            if (user != null) {
                TextNombre.setText(user.getNombre());
                TextApellido.setText(user.getApellidoP());
                TextCorreo.setText(user.getCorreo());
                TextContra.setText(user.getPassword());
            }
        } else {
            System.out.println("Cargando vista en Modo Registro");
            BtnSig.setText("Siguiente");
        }
    }

    @FXML
    private void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        SharedData data = SharedData.getInstance();
        if (data.isModoEdicion()) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Use_4.fxml"));

            Scene scene = ((Button) actionEvent.getSource()).getScene();
            Stage stage = (Stage) scene.getWindow();

            Scene nextScene = new Scene(fxmlLoader.load());
            stage.setTitle("CineSync - Panel de Usuario");
            stage.setScene(nextScene);
        } else {
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
    private void BtnSigAction(ActionEvent actionEvent) throws IOException {
        SharedData data = SharedData.getInstance();
        if (TextNombre.getText().isEmpty() || TextContra.getText().isEmpty() ||
                TextApellido.getText().isEmpty() || TextCorreo.getText().isEmpty()) {
            System.out.println("Error: Rellena los campos");
            return;
        }

        if (data.isModoEdicion()) {
            Usuario user = data.getUsuarioLogueado();
            if (user != null) {
                user.setNombre(TextNombre.getText());
                user.setApellidoP(TextApellido.getText());
                user.setCorreo(TextCorreo.getText());
                user.setPassword(TextContra.getText());
                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Use_4.fxml"));
                Scene scene = ((Button) actionEvent.getSource()).getScene();
                Stage stage = (Stage) scene.getWindow();
                Scene nextScene = new Scene(fxmlLoader.load());
                stage.setTitle("CineSync - Panel de Usuario");
                stage.setScene(nextScene);}
            } else {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/sesion_2.fxml"));

                    Scene scene = ((Button) actionEvent.getSource()).getScene();
                    Stage stage = (Stage) scene.getWindow();

                    Scene nextScene = new Scene(fxmlLoader.load());
                    stage.setTitle("CineSync - Iniciar Sesion");
                    stage.setScene(nextScene);
                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setNombre(TextNombre.getText());
                    nuevoUsuario.setApellidoP(TextApellido.getText());
                    nuevoUsuario.setCorreo(TextCorreo.getText());
                    nuevoUsuario.setPassword(TextContra.getText());
                    data.setUsuarioLogueado(nuevoUsuario);

                } catch (IOException e) {
                    System.err.println("Error al cargar la vista: ");
                    e.printStackTrace();
                }

            }
        }
    }