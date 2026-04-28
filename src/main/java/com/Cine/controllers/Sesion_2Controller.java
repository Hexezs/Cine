package com.Cine.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        System.out.println("Pantalla de sesión cargada");

        // Ejemplo: llenar el ComboBox
        CmbxUsuario.getItems().addAll(
                "Usuario 1",
                "Usuario 2",
                "Usuario 3"
        );
    }

    @FXML
    private void BtnAtrasAction() {
        System.out.println("Click en Atrás");
        // Aquí luego puedes regresar a la pantalla anterior
    }

    @FXML
    private void BtnCancelarAction() {
        System.out.println("Click en Cancelar");

        // Limpiar campos
        TextCorreo.clear();
        TextContra.clear();
        CmbxUsuario.getSelectionModel().clearSelection();
    }

    @FXML
    private void BtnSigAction() {
        String correo = TextCorreo.getText();
        String contra = TextContra.getText();
        String usuario = CmbxUsuario.getValue();

        System.out.println("Correo: " + correo);
        System.out.println("Contraseña: " + contra);
        System.out.println("Usuario seleccionado: " + usuario);

        // Aquí después:
        // 👉 validas
        // 👉 llamas al service
        // 👉 haces login con BD
    }

    @FXML
    private void CmbxUsuarioAction() {
        System.out.println("Seleccionaste: " + CmbxUsuario.getValue());
    }
}
