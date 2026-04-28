package com.Cine.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private void BtnAtrasAction() {
        System.out.println("Click en Atrás");
        // Aquí luego puedes regresar a la pantalla anterior
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

        // Aquí después:
        // 👉 validas datos
        // 👉 creas DTO
        // 👉 llamas al service
        // 👉 guardas en BD con Hibernate
    }
}