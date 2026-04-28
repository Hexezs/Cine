package com.Cine.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    private void BtnSesionAction() {
        System.out.println("Click en Iniciar sesión");

        // Aquí después puedes abrir login.fxml
        // cambiar ventana o escena
    }

    @FXML
    private void BtnRegistroAction() {
        System.out.println("Click en Registrarse");

        // Aquí después puedes abrir registro.fxml
    }
}
