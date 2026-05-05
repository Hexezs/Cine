package com.Cine.controllers;

import com.Cine.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SelecPeli_7Controller {
    @FXML
    private Button BtnCancelar;
    @FXML
    private Button BtnSig;
    @FXML
    private ComboBox<String> CmbxLetraAsiento;
    @FXML
    private Button BtnAtras;
    @FXML
    public void initialize(){

    }
    @FXML
    public void BtnAtrasAction(ActionEvent actionEvent) throws IOException {
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Pinci_5.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());

        stage.setTitle("CineSync - Seleccionar Pelicula");
        stage.setScene(nextScene);
    }
    @FXML
    public void BtnCancelarAction(ActionEvent actionEvent) {
    }
    @FXML
    public void BtnSigAction(ActionEvent actionEvent) throws IOException {
        Scene scene = ((Button) actionEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Registro_8.fxml"));
        Scene nextScene = new Scene(fxmlLoader.load());

        stage.setTitle("CineSync - Registro");
        stage.setScene(nextScene);
    }
    @FXML
    public void CmbxLetraAsientoAction(ActionEvent actionEvent) {
    }
}
